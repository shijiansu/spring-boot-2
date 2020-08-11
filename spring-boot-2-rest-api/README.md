# What is HATEOAS

- <https://jozdoo.github.io/rest/2016/09/22/REST-HATEOAS.html>

HATEOAS 是 Hypermedia As The Engine Of Application State 的缩写，从字面上理解是 “超媒体即是应用状态引擎” 。其原则就是客户端与服务器的交互完全由超媒体动态提供，客户端无需事先了解如何与数据或者服务器交互。相反的，在一些RPC服务或者Redis,Mysql等软件，需要事先了解接口定义或者特定的交互语法。

HATEOAS 是 REST(Representational state transfer) 的约束之一

在Richardson Maturity Model模型中，将RESTful分为四步,其中第四步 Hypermedia Controls 也就是HATEOAS。

- 第一个层次（Level 0）的 Web 服务只是使用 HTTP 作为传输方式，实际上只是远程方法调用（RPC）的一种具体形式。SOAP 和 XML-RPC 都属于此类。
- 第二个层次（Level 1）的 Web 服务引入了资源的概念。每个资源有对应的标识符和表达。
- 第三个层次（Level 2）的 Web 服务使用不同的 HTTP 方法来进行不同的操作，并且使用 HTTP 状态码来表示不同的结果。如 HTTP GET 方法来获取资源，HTTP DELETE 方法来删除资源。
- 第四个层次（Level 3）的 Web 服务使用 HATEOAS。在资源的表达中包含了链接信息。客户端可以根据链接来发现可以执行的动作。

REST的设计者Roy T. Fielding在博客 `REST APIs must be hypertext-driven` 中的几点原则强调非HATEOAS的系统不能称为RESTful。

- REST API决不能定义固定的资源名称或者层次关系
- 使用REST API应该只需要知道初始URI（书签）和一系列针对目标用户的标准媒体类型

JSON作为流行的数据交换格式，产生了许多为其定做的HATEOAS规范，下面是可以采用的规范

- HAL - Hypertext Application Language, 已经被Spring-HATEOAS所实现
- Siren
- Collection
- JSON-LD

## HAL保留属性

- _links - 属性 “_links” 是可选。它是一个对象，它包含了一个或多个属性，每个属性可以是对象或者数组。
  - _links的每个属性都一个超链接对象，这些对象包含了资源至URL，他们有下列几个属性
    - href –string 必填项，它的内容可以是URL 或者URL模板。
    - templated –bool 可选项，默认为false，如果href是URL模板则templated必须为true
    - type –string 可选项，它用来表示资源类型
    - deprecation –string 可选项，表示该对象会在未来废弃
    - name –string 可选项， 可能当作第二个key，当需要选择拥有相同name的链接时
    - profile –string 可选项，简要说明链接的内容
    - title –string 可选项，用用户可读的语音来描述资源的主题
    - hreflang –string 可选项，用来表明资源的语言
- _embedded - 属性 “_embedded”是可选的。它是一个对象，它包含了一个或多个属性，每个属性是可以对象或者数组。

文档例子

```text
   GET /orders HTTP/1.1
   Host: example.org
   Accept: application/hal+json

   HTTP/1.1 200 OK
   Content-Type: application/hal+json

   {
     "_links": {
       "self": { "href": "/orders" },
       "next": { "href": "/orders?page=2" },
       "find": { "href": "/orders{?id}", "templated": true }
     },
     "_embedded": {
       "orders": [{
           "_links": {
             "self": { "href": "/orders/123" },
             "basket": { "href": "/baskets/98712" },
             "customer": { "href": "/customers/7809" }
           },
           "total": 30.00,
           "currency": "USD",
           "status": "shipped",
         },{
           "_links": {
             "self": { "href": "/orders/124" },
             "basket": { "href": "/baskets/97213" },
             "customer": { "href": "/customers/12369" }
           },
           "total": 20.00,
           "currency": "USD",
           "status": "processing"
       }]
     },
     "currentlyProcessing": 14,
     "shippedToday": 20
   }
```

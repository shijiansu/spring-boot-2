# About .dockerignore

It is to ignore items to copy to docker context when building docker image,
so that can speed up the building. Bascially it is same as .gitignore,
besides we keep target/ and build/ folders because they are with geenrated artifact

# Control startup and shutdown order in Compose

`depends_on` option - <https://docs.docker.com/compose/startup-order/>

> However, for startup Compose does not wait until a container is “ready” (whatever that means for your particular application) - only until it’s running. There’s a good reason for this.

# About .dockerignore

It is to ignore items to copy to docker context when building docker image,
so that can speed up the building. Bascially it is same as .gitignore,
besides we keep target/ and build/ folders because they are with geenrated artifact

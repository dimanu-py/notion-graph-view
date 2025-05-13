# Angular Installation on Docker

The main goal when setting up the frontend project is to avoid installing
Node and Angular dependencies in the local machine, leveraging this to a Docker 
container.

The `CMD` command in the [`Dockerfile`](Dockerfile.init) will be the default command we will use to start
the server once we have everything set up.

## Start from scratch

If you haven't created the container nor installed Angular in it, we will follow these
steps:

1. **Create an Angular workspace**: from the [_frontend_](../frontend) directory, run the following command:

    ```bash
    docker compose run --rm --user "$(id -u):$(id -g)" angular-dev ng new src --directory . --skip-git --defaults --skip-install
    ```

   > [!INFO]
   > After running this command, the *node_modules* folder might be created as root. You would need to change
   > the owner before running the next command. This may vary depending on the OS, in Linux you can run 
   > `sudo chown -R $(id -u):$(id -g) ./node_modules`

2. **Install dependencies**: run the following command to install the dependencies:

    ```bash
    docker compose run --rm angular-dev npm install
    ```

## Running the Development Server

After setting up the Angular application, you can run the development server using one of the following methods:

The easiest way to run the development server is to use the `angular-dev` service, which is configured to automatically start the Angular development server:

```bash
docker compose up -d angular-dev
```

This will start the development server and make it accessible at http://localhost:4200.


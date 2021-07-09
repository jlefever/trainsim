# Train Sim

Simulate purchasing train tickets.

## Development

First build the frontend in watch mode.

```
cd trainsim-client
npm install
npm run watch
```

Now in a new shell, start the application with `docker-compose up`. Visit `localhost:8000` in your browser. If you make any changes to the frontend, simply save the file and you will see your changes reflected in the browser. If you make any changes to the (Java) backend, simply restart the service with `docker-compose restart trainsim-api`.
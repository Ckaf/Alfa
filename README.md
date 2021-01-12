Specify your GIPHY token in application. properties instead of " * " in the ```feign.client.gif.token property```, also specify the token from openexchangerates in the property ```feign.client.rank.token```.

Go to the root of the project and run the program using ```gradle bootRun```.

After that, you can go to ```http://localhost:9090/app/usd```.

### Disclaimer
In order to change the currency, you need to change the url, but this will work if your token from openexchangerates is a developer token.

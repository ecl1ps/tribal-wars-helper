# tribal-wars-helper
TWH is a desktop app which aids with various tedious tasks in a game **Tribal Wars**, especially when your territory grows larger.

## Features
* Displays your villages, their resources and population.
* Shows incomming attacks to player's villages with unit types and attack start time.
* Calculates if an attack contains a noble.
* Sends SMS notification when new attack is detected.
* Sends raiding parties with single click.
* Remembers resources of previously raided villages so you can easily pick your next target.

![Incoming attack](./doc/false%20identification.png)

## Priciples
App uses web scraping using player's session. Session is read from browser where user is signed in.
It also uses various bot-detection prevention techniques described in whitepapers [Evasive Bots Masquerading as Human Beings on the Web](./doc/evasive.pdf) and [Web Tap: Detecting Covert Web Traffic](./doc/p019-borders.pdf).

## Disclaimer
Use at your own risk. This is a grey area as you might get your account banned for using additional tools.

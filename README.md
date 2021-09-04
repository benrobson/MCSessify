# MCSessify
A plugin that blocks access for those who don't have the verification token from the Server Administrator. 

## Commands
| Command           | Description                                                                                 | Permission            |
|-------------------|---------------------------------------------------------------------------------------------|-----------------------|
| /verify [token]   | Gain access using the token for the server event.                                           |                       |
| /token [view/set] | * View the current set token. * Set a new token (setting new token requires server reload). | mcsessify.token OR op |

## Restrictions
If a user isn't verified they will be stuck and not able to do anything until they are verified.
They cannot:
* Move
* Pick up items
* Process any commands except `/verify (/token and /reload if you have the permission OR op)`
* Drop items
* Interact
* Switch slots
* Place Blocks
* Send chat messages
* Cannot be damaged

## config.yml
| Key          | Value      | Description                        |
|--------------|------------|------------------------------------|
| SESSIONTOKEN | TE$T       | The token to verify users against. |
| EVENTNAME    | Test Event | The name of the event.             |
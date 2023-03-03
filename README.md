# UserLog

UserLog is a plugin for logging chat messages on your Minecraft server.

## Problem

The plugin currently has an issue with slowing down message sending, which needs to be fixed.

## Configuration

UserLog has a configuration file `config.yml` with the following settings:

```yaml
mysql:
  hostname: 127.0.0.1
  username: root
  password: "password"
  database: "database"
  table: "table"
  port: 3306
  options: "?useSSL=false"
plugin:
  server: "Server Name"
  joinmsg: "JoinPlayer"
  quitmsg: "QuitPlayer"
```
- `mysql` - section contains the MySQL server configuration.
- `plugin` - section contains the plugin-specific configuration.

## License

This plugin is licensed under the MIT License.

## Problems

At the moment, the plugin has a problem - delayed sending messages in the chat, we do not know how to fix it. If you know how to fix it, please help us!

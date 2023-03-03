# UserLog

UserLog is a plugin for logging the joining and exiting of players on the Minecraft server

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

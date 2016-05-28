# qEssentialsReloaded ![build](https://img.shields.io/badge/version-1.3.5-brightgreen.svg) ![build](https://img.shields.io/badge/mcver-1.9.2-green.svg) ![build](https://img.shields.io/badge/engine-spigot%2C%20paper-blue.svg) 
It's a fully open-source project providing a basic commands and tools.
Project is founded on Maven technology so it's so easy to add this plugin to dependencies.

## Maven Dependency
To add project to your Maven dependencies you must define a repository first.
```xml
    <repositories>
        <repository>
            <!-- qEssentials Repository -->
            <id>qessentials-repo</id>
            <url>http://kavzaq.cba.pl/content/repositories/public/</url>
        </repository>
    </repositories>
```
Next just add a dependency with current version of project.
```xml
    <dependencies>
        <!-- qEssentials Dependency -->
        <dependency>
            <groupId>me.kavzaq</groupId>
            <artifactId>qEssentialsReloaded</artifactId>
            <version>1.3.5</version> <!-- Here define version -->
        </dependency>
    </dependencies>
```
Build a project and use a project safely.

## MCStats
![Global Stats](http://i.mcstats.org/qEssentialsReloaded/Global+Statistics.png)

![Software](http://i.mcstats.org/qEssentialsReloaded/Server+Software.png)

![Version Demo](http://i.mcstats.org/qEssentialsReloaded/Version+Demographics.png)

[Click for more informations (mcstats.com)](http://mcstats.org/plugin/qEssentialsReloaded)

## Kit/item syntax provided in qEssentials
It's not an easy system, we know it. There's some help about it.
```yaml
<material:data> <amount> [name] [loreline1;loreline2] [enchantment:1;enchantment:2]
```

You can add `*` character to bypass some function.
```yaml
<material:data> <amount> * [loreline1;loreline2] [enchantment:1;enchantment:2]
```

If you dont want to have `lore` or `enchantments` in your item, just dont add it. Character `*` isn't required.
```yaml
<material:data> <amount> [name]
```

Some examples: `some names are in polish, because i cant do new pictures now`
```yaml
apple 1 &4Jablko_Zaglady &cBardzo;&cstraszne_jablko;&coj_bardzo... sharpness:10;unbreaking:3
```
![Example1](https://i.gyazo.com/79392dcecec2548fa1aa5437c8e57544.png)

```yaml
golden_apple:1 5 &6&lExcalibur * sharpness:1
```
![Example2](https://i.gyazo.com/7164fff1c5c072ddf2b5686e1be3f9c4.png)

```yaml
gold_sword 1 * * unbreaking:1000
```
![Example3](https://i.gyazo.com/d3095c6b46b3f9a387649947f8800750.png)
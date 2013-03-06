CrashPrevention
===============

Prevent OutOfMemory crashes on your CraftBukkit server!

This plugin can help against server crashes from Out of memory expections, especially those that usually have high uptime.

Every 10 seconds the plugin will check the RAM available on your server, if it's below an amount defined in the config it will trigger a shutdown (Restart).

To actually make this restart your server, you must use the startup scripts provided.

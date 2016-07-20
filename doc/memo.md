## Functions
Read common setting
=> SettingData.class

Read Inventories
=> Inventory
=> Inventory.readFiles

UI
=> GuiMain


## Directories
F:\bestiary
./files/*.wmv,*.jpg
./common.conf
./hoge.txt
./piyo.txt

## Input

### common setting
execute-path (Map: extension, program)
wmv, "c:\hoge\piyo\vlc"
inventories = F:\bestiary\hoge.txt,F:\bestiary\piyo.txt
directories-path = dir,dir,dir...

### Inventory
UTF-8, TSV
filename, required
title
name
url
tags

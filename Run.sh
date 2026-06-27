###
# @Author: xixi_
# @Date: 2026-06-27 08:56:59
# @LastEditors: xixi_
# @LastEditTime: 2026-06-27 14:44:03
# @FilePath: /Xncut-Server/Run.sh
# Copyright (c) 2017-2026 by xixi_ , All Rights Reserved.
###

javac -d Out openGAME/Recorder/RecorderApplication.java
javac -d Out -cp Out Src/XncutMain.java

java -cp Out XncutMain
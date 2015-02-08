/*
Navicat MySQL Data Transfer

Source Server         : gaoxin
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : ec

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-02-08 14:45:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `custinfo`
-- ----------------------------
DROP TABLE IF EXISTS `custinfo`;
CREATE TABLE `custinfo` (
  `CUSTID` int(11) NOT NULL COMMENT '客户ID',
  `CUSTNO` varchar(18) NOT NULL COMMENT '客户编号',
  `INVTP` varchar(1) NOT NULL COMMENT '投资人类别',
  `INVNM` varchar(120) NOT NULL COMMENT '客户名称',
  `IDTP` varchar(1) NOT NULL COMMENT '证件类型',
  `IDNO` varchar(30) NOT NULL COMMENT '证件号码',
  `MOBILENO` varchar(24) DEFAULT NULL COMMENT '手机',
  `EMAIL` varchar(40) DEFAULT NULL COMMENT '电子邮件',
  `CUSTST` varchar(1) NOT NULL COMMENT '状态''N''正常 ''C''撤销',
  `PASSWD` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `LOGINTIME` date DEFAULT NULL COMMENT '上次登录时间',
  `LOGINCOUNT` tinyint(2) DEFAULT NULL COMMENT '登入总次数',
  `PASSWDERR` tinyint(2) NOT NULL COMMENT '密码错误次数',
  `OPENDT` varchar(8) DEFAULT NULL COMMENT '开户日期',
  `UPDATETIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间戳',
  `TRADEPWD` varchar(32) DEFAULT NULL COMMENT '交易密码',
  PRIMARY KEY (`CUSTNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户资料表';

-- ----------------------------
-- Records of custinfo
-- ----------------------------

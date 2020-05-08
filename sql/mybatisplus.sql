/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50628
 Source Host           : 127.0.0.1:3306
 Source Schema         : mybatisplus

 Target Server Type    : MySQL
 Target Server Version : 50628
 File Encoding         : 65001

 Date: 16/11/2019 16:26:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int(20) NULL DEFAULT NULL,
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (12, '小', 25, '123', '2019-11-14 14:21:02', 0);
INSERT INTO `user` VALUES (13, '成路涛', 26, '18813024881', '2019-11-14 14:01:02', 0);
INSERT INTO `user` VALUES (14, '成路涛2', 11, '18813024881', '2019-11-14 14:01:02', 0);
INSERT INTO `user` VALUES (15, '路', 26, '1234S', '2019-11-14 14:01:22', 0);
INSERT INTO `user` VALUES (16, '张路', 30, '112', '2019-11-14 14:04:51', 0);
INSERT INTO `user` VALUES (17, '张海路', 13, '112', '2019-11-14 14:04:51', 0);
INSERT INTO `user` VALUES (18, '王五', 33, '123941243', '2019-11-14 14:33:21', 0);
INSERT INTO `user` VALUES (19, '王五1', 32, '1239412431', '2019-11-14 14:33:21', 0);
INSERT INTO `user` VALUES (20, '王五4', 1111, '123941243', '2019-11-14 14:34:12', 0);
INSERT INTO `user` VALUES (21, '王五3', 31, '1239412431', '2019-11-14 14:34:12', 1);
INSERT INTO `user` VALUES (22, '成路涛6', 10, '18813024889', '2019-11-14 15:35:14', 0);
INSERT INTO `user` VALUES (23, '成路涛7', 10, '18813024889', '2019-11-14 15:36:47', 0);

SET FOREIGN_KEY_CHECKS = 1;

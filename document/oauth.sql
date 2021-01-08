/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Schema         : oauth

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 08/01/2021 17:27:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端所能访问的资源id集合,多个资源时用逗号(,)',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: \"read,write\".@EnableGlobalMethodSecurity(prePostEnabled = true)启用方法级权限控制,然后在方法上注解标识@PreAuthorize(\"#oauth2.hasScope(\'read\')\")',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端的重定向URI',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔,@PreAuthorize(\"hasAuthority(\'admin\')\")可以在方法上标志',
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设置用户是否自动Approval操作, 默认值为 \'false\', 可选值包括 \'true\',\'false\', \'read\',\'write\'.\r\n该字段只适用于grant_type=\"authorization_code\"的情况',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '终端信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('app', NULL, '$2a$10$oZG2v0IonEhrDSZ5g1dX9.OYKJ9dH8GQ.uk.Mh24PlOP5a4RlcNAi', 'app', 'authorization_code,password,refresh_token,client_credentials', 'https://www.baidu.com', NULL, 7200, 7200, NULL, 'false');
INSERT INTO `oauth_client_details` VALUES ('phone', NULL, '$2a$10$oZG2v0IonEhrDSZ5g1dX9.OYKJ9dH8GQ.uk.Mh24PlOP5a4RlcNAi', 'phone', 'authorization_code,password,refresh_token,client_credentials,phone', 'https://www.baidu.com', NULL, 7200, 7200, NULL, 'false');
INSERT INTO `oauth_client_details` VALUES ('server', NULL, '$2a$10$oZG2v0IonEhrDSZ5g1dX9.OYKJ9dH8GQ.uk.Mh24PlOP5a4RlcNAi', 'server', 'authorization_code,password,refresh_token,client_credentials', 'https://www.baidu.com', NULL, 7200, 7200, NULL, 'false');
INSERT INTO `oauth_client_details` VALUES ('web', NULL, '$2a$10$oZG2v0IonEhrDSZ5g1dX9.OYKJ9dH8GQ.uk.Mh24PlOP5a4RlcNAi', 'web', 'authorization_code,password,refresh_token,client_credentials', 'https://www.baidu.com', NULL, 7200, 7200, NULL, 'false');

-- ----------------------------
-- Table structure for six_base_user
-- ----------------------------
DROP TABLE IF EXISTS `six_base_user`;
CREATE TABLE `six_base_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信-openId',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信-用户昵称',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '微信-用户性别 0: 未知, 1: 男性, 2:女性',
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'en: 英文, zh_CN: 简体中文, zh_TW: 繁体中文',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信-用户所在国家',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信-用户所在省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信-用户所在城市',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信-用户头像',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信-unionId',
  `account_non_expired` tinyint(1) NULL DEFAULT 1 COMMENT '账户过期状态',
  `account_non_locked` tinyint(1) NULL DEFAULT 1 COMMENT '账户锁定状态',
  `credentials_non_expired` tinyint(1) NULL DEFAULT 1 COMMENT '凭证过期状态',
  `enabled` tinyint(1) NULL DEFAULT 1,
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of six_base_user
-- ----------------------------
INSERT INTO `six_base_user` VALUES (1, 'admin', '$2a$10$bAFotFepA1N8yIpbBMy4iegJUvXcmbeQRQwO/hkrc.6gJt6m.S2ra', NULL, 'a', '0', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, '2021-01-05 07:56:57', '2021-01-07 09:43:26', 0);
INSERT INTO `six_base_user` VALUES (2, 'dubbo', '$2a$10$EsAfPKw9ecGQsdQZNC0IoeTin2P/uKFsB7Tzh3THP/IvnIJJ1NsTe', NULL, 'b', '0', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, '2021-01-05 07:58:04', '2021-01-07 09:43:28', 0);
INSERT INTO `six_base_user` VALUES (3, 'redis', '$2a$10$qY6epcE3mkNXD56HDczQWe3p6rWdQMCi1AFYMJBOwlRO3BqaK9gJ6', NULL, 'redis', '0', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, '2021-01-07 09:44:03', '2021-01-08 01:07:30', 0);
INSERT INTO `six_base_user` VALUES (4, 'xx', '$2a$10$HMDx47Pgg5hAwf.DrCPlxui8UFOuMhi343wNoLCj.ZCGu2XR/DmqS', NULL, 'xx', '0', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, '2021-01-07 09:57:25', '2021-01-07 09:58:31', 0);
INSERT INTO `six_base_user` VALUES (5, 'rabbit', '$2a$10$IPfjNMOk230mWWHpmcjtqeSxCRz1p/wpIMKK3LH8pdHAFkqxqROEC', NULL, '1o7uvu', '0', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, '2021-01-07 09:57:25', '2021-01-07 09:57:25', 0);

SET FOREIGN_KEY_CHECKS = 1;

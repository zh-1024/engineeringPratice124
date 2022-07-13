-- ----------------------------
-- database `youddit`
-- ----------------------------
CREATE DATABASE `youddit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `followers_num` bigint NOT NULL DEFAULT '0',
  `following_num` bigint NOT NULL DEFAULT '0',
  `profile` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `private_message`
-- ----------------------------
DROP TABLE IF EXISTS `private_message`;
CREATE TABLE `private_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender_id` bigint NOT NULL,
  `receive_id` bigint NOT NULL,
  `message` varchar(255) NOT NULL,
  `send_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `label`
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `label_id` bigint NOT NULL AUTO_INCREMENT,
  `label_name` varchar(50) NOT NULL,
  `use_num` bigint NOT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `inform`
-- ----------------------------
DROP TABLE IF EXISTS `inform`;
CREATE TABLE `inform` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `new_follow_num` bigint DEFAULT NULL,
  `new_private_message_num` bigint DEFAULT NULL,
  `new_comment_num` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `image_id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `follow`
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user1_id` bigint NOT NULL,
  `user2_id` bigint NOT NULL,
  `follow_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `content_like_user`
-- ----------------------------
DROP TABLE IF EXISTS `content_like_user`;
CREATE TABLE `content_like_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `content_label`
-- ----------------------------
DROP TABLE IF EXISTS `content_label`;
CREATE TABLE `content_label` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_id` bigint NOT NULL,
  `label_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `content_image`
-- ----------------------------
DROP TABLE IF EXISTS `content_image`;
CREATE TABLE `content_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_id` bigint NOT NULL,
  `image_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `content_comment`
-- ----------------------------
DROP TABLE IF EXISTS `content_comment`;
CREATE TABLE `content_comment` (
  `content_comment_id` bigint NOT NULL AUTO_INCREMENT,
  `content_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `comment_info` varchar(255) NOT NULL,
  `comment_time` datetime NOT NULL,
  PRIMARY KEY (`content_comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `content_collect_user`
-- ----------------------------
DROP TABLE IF EXISTS `content_collect_user`;
CREATE TABLE `content_collect_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `content_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `info_describe` varchar(255) NOT NULL,
  `views_num` bigint NOT NULL,
  `like_num` bigint NOT NULL,
  `collect_num` bigint NOT NULL,
  `post_time` datetime DEFAULT NULL,
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for `comment_comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment_comment`;
CREATE TABLE `comment_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_comment_id` bigint NOT NULL,
  `comment_comment_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `comment_time` datetime NOT NULL,
  `level` tinyint NOT NULL,
  `comment_info` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


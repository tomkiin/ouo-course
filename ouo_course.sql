/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : ouo_course

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 20/02/2020 18:06:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ouo_admin
-- ----------------------------
DROP TABLE IF EXISTS `ouo_admin`;
CREATE TABLE `ouo_admin` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员Id',
  `admin_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `admin_password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `admin_privilege` int(11) NOT NULL COMMENT '角色\r\n二进制表示权限\r\n1-系管理\r\n2-专业管理\r\n4-班级管理\r\n8-学生管理\r\n16-教师管理\r\n32-课程管理\r\n64-选课管理\r\n128-管理员管理',
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE KEY `idx_admin_username` (`admin_username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ouo_admin
-- ----------------------------
BEGIN;
INSERT INTO `ouo_admin` VALUES (3, 'tomkiin', '4c4b3a5ca87cfb49b49bdbb83e86bffb', 255);
INSERT INTO `ouo_admin` VALUES (6, 'tomkiin1', '4c4b3a5ca87cfb49b49bdbb83e86bffb', 7);
COMMIT;

-- ----------------------------
-- Table structure for ouo_class
-- ----------------------------
DROP TABLE IF EXISTS `ouo_class`;
CREATE TABLE `ouo_class` (
  `class_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '班级Id',
  `class_major_id` int(10) unsigned NOT NULL COMMENT '专业Id',
  `class_grade` int(10) unsigned NOT NULL COMMENT '年级',
  `class_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`class_id`) USING BTREE,
  KEY `fk_major_id` (`class_major_id`) USING BTREE,
  KEY `idx_class_name` (`class_name`) USING BTREE,
  CONSTRAINT `fk_major_id` FOREIGN KEY (`class_major_id`) REFERENCES `ouo_major` (`major_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ouo_class
-- ----------------------------
BEGIN;
INSERT INTO `ouo_class` VALUES (24, 46, 2017, 'B170407');
INSERT INTO `ouo_class` VALUES (25, 47, 2019, '测试班级');
COMMIT;

-- ----------------------------
-- Table structure for ouo_course
-- ----------------------------
DROP TABLE IF EXISTS `ouo_course`;
CREATE TABLE `ouo_course` (
  `course_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程Id',
  `course_teacher_id` int(10) unsigned NOT NULL COMMENT '授课教师Id',
  `course_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名称',
  `course_grade` int(10) unsigned NOT NULL COMMENT '授课年级',
  `course_time` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上课时间 星期几-第几节-几节课',
  `course_location` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上课地址',
  `course_credit` int(10) unsigned NOT NULL COMMENT '学分',
  `course_selected_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '已选人数',
  `course_max_size` int(10) unsigned NOT NULL COMMENT '最大容量',
  `course_exam_date` datetime DEFAULT NULL COMMENT '考试时间',
  `course_exam_location` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '考试地点',
  PRIMARY KEY (`course_id`) USING BTREE,
  KEY `fk_course_teacher_id` (`course_teacher_id`) USING BTREE,
  KEY `idx_course_name` (`course_name`) USING BTREE,
  CONSTRAINT `fk_course_teacher_id` FOREIGN KEY (`course_teacher_id`) REFERENCES `ouo_teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ouo_course
-- ----------------------------
BEGIN;
INSERT INTO `ouo_course` VALUES (9, 10, 'Java程序设计', 2017, '1-1-2', '教3-204', 2, 0, 50, '2020-06-30 00:00:00', '教3-204');
INSERT INTO `ouo_course` VALUES (10, 10, '数据库系统教程', 2017, '2-3-2', '教2-202', 2, 0, 50, '2020-06-30 00:00:00', '教2-202');
INSERT INTO `ouo_course` VALUES (11, 10, '网络编程', 2017, '4-2-2', '教4-404', 2, 0, 50, '2020-06-30 00:00:00', '教4-404');
INSERT INTO `ouo_course` VALUES (12, 10, '测试课程', 2017, '2-6-2', '教1-101', 2, 0, 50, NULL, NULL);
INSERT INTO `ouo_course` VALUES (13, 11, '测试课程2', 2018, '2-2-2', '教2-206', 2, 0, 50, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for ouo_department
-- ----------------------------
DROP TABLE IF EXISTS `ouo_department`;
CREATE TABLE `ouo_department` (
  `department_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '系Id',
  `department_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系名称',
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ouo_department
-- ----------------------------
BEGIN;
INSERT INTO `ouo_department` VALUES (45, '计算机学院');
INSERT INTO `ouo_department` VALUES (46, '测试院系1');
INSERT INTO `ouo_department` VALUES (47, '测试院系2');
INSERT INTO `ouo_department` VALUES (48, '测试院系3');
INSERT INTO `ouo_department` VALUES (49, '测试院系4');
INSERT INTO `ouo_department` VALUES (50, '测试院系5');
INSERT INTO `ouo_department` VALUES (51, '测试院系6');
INSERT INTO `ouo_department` VALUES (52, '测试院系7');
INSERT INTO `ouo_department` VALUES (53, '测试院系8');
INSERT INTO `ouo_department` VALUES (54, '测试院系9');
INSERT INTO `ouo_department` VALUES (55, '测试院系10');
INSERT INTO `ouo_department` VALUES (56, '测试院系11');
INSERT INTO `ouo_department` VALUES (57, '测试院系12');
INSERT INTO `ouo_department` VALUES (58, '测试院系13');
INSERT INTO `ouo_department` VALUES (59, '测试院系14');
INSERT INTO `ouo_department` VALUES (60, '测试院系15');
INSERT INTO `ouo_department` VALUES (61, '测试院系16');
INSERT INTO `ouo_department` VALUES (62, '测试院系17');
INSERT INTO `ouo_department` VALUES (63, '测试院系18');
INSERT INTO `ouo_department` VALUES (64, '测试院系19');
INSERT INTO `ouo_department` VALUES (65, '测试院系20');
INSERT INTO `ouo_department` VALUES (66, '测试院系21');
COMMIT;

-- ----------------------------
-- Table structure for ouo_major
-- ----------------------------
DROP TABLE IF EXISTS `ouo_major`;
CREATE TABLE `ouo_major` (
  `major_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '专业Id',
  `major_department_id` int(10) unsigned NOT NULL COMMENT '系Id',
  `major_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专业名称',
  PRIMARY KEY (`major_id`) USING BTREE,
  KEY `fk_major_department_id` (`major_department_id`) USING BTREE,
  KEY `idx_major_name` (`major_name`) USING BTREE,
  CONSTRAINT `fk_major_department_id` FOREIGN KEY (`major_department_id`) REFERENCES `ouo_department` (`department_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ouo_major
-- ----------------------------
BEGIN;
INSERT INTO `ouo_major` VALUES (46, 45, '信息安全');
INSERT INTO `ouo_major` VALUES (47, 46, '测试专业1');
INSERT INTO `ouo_major` VALUES (48, 47, '测试专业2');
INSERT INTO `ouo_major` VALUES (49, 48, '测试专业3');
INSERT INTO `ouo_major` VALUES (50, 49, '测试专业4');
COMMIT;

-- ----------------------------
-- Table structure for ouo_student
-- ----------------------------
DROP TABLE IF EXISTS `ouo_student`;
CREATE TABLE `ouo_student` (
  `student_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '学生Id',
  `student_class_id` int(10) unsigned NOT NULL COMMENT '班级Id',
  `student_number` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `student_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `student_password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `student_email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电子邮箱',
  `student_birthday` datetime DEFAULT NULL COMMENT '生日',
  `student_sex` tinyint(1) unsigned NOT NULL COMMENT '性别',
  `student_last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  PRIMARY KEY (`student_id`) USING BTREE,
  UNIQUE KEY `idx_student_number` (`student_number`) USING BTREE,
  KEY `fk_student_class_id` (`student_class_id`) USING BTREE,
  KEY `idx_student_name` (`student_name`) USING BTREE,
  CONSTRAINT `fk_student_class_id` FOREIGN KEY (`student_class_id`) REFERENCES `ouo_class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ouo_student
-- ----------------------------
BEGIN;
INSERT INTO `ouo_student` VALUES (31, 24, 'B12345678', '唐某', '4c4b3a5ca87cfb49b49bdbb83e86bffb', '1136410208@qq.com', '2020-02-19 00:00:00', 1, '2020-02-20 17:58:54');
COMMIT;

-- ----------------------------
-- Table structure for ouo_student_course
-- ----------------------------
DROP TABLE IF EXISTS `ouo_student_course`;
CREATE TABLE `ouo_student_course` (
  `sc_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '选课Id',
  `sc_student_id` int(10) unsigned NOT NULL COMMENT '学生Id',
  `sc_course_id` int(10) unsigned NOT NULL COMMENT '课程Id',
  `sc_daily_score` int(10) unsigned DEFAULT NULL COMMENT '日常表现分',
  `sc_exam_score` int(10) unsigned DEFAULT NULL COMMENT '期末测试分',
  `sc_score` int(10) unsigned DEFAULT NULL COMMENT '总成绩',
  PRIMARY KEY (`sc_id`) USING BTREE,
  KEY `fk_sc_course_id` (`sc_course_id`) USING BTREE,
  KEY `fk_sc_student_id` (`sc_student_id`) USING BTREE,
  CONSTRAINT `fk_sc_course_id` FOREIGN KEY (`sc_course_id`) REFERENCES `ouo_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_sc_student_id` FOREIGN KEY (`sc_student_id`) REFERENCES `ouo_student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for ouo_teacher
-- ----------------------------
DROP TABLE IF EXISTS `ouo_teacher`;
CREATE TABLE `ouo_teacher` (
  `teacher_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '教师Id',
  `teacher_department_id` int(10) unsigned NOT NULL COMMENT '系Id',
  `teacher_number` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工号',
  `teacher_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教师姓名',
  `teacher_password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`teacher_id`) USING BTREE,
  UNIQUE KEY `idx_teacher_number` (`teacher_number`) USING BTREE,
  KEY `fk_teacher_department_id` (`teacher_department_id`) USING BTREE,
  CONSTRAINT `fk_teacher_department_id` FOREIGN KEY (`teacher_department_id`) REFERENCES `ouo_department` (`department_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ouo_teacher
-- ----------------------------
BEGIN;
INSERT INTO `ouo_teacher` VALUES (10, 45, 'B12345678', '王老师', '4c4b3a5ca87cfb49b49bdbb83e86bffb');
INSERT INTO `ouo_teacher` VALUES (11, 46, 'B11111111', '测试老师', '4c4b3a5ca87cfb49b49bdbb83e86bffb');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

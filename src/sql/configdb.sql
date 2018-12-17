 

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant`  (
  `tenant_id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tenant_key` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `db_url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `db_user` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `db_password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `db_public_key` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tenant
-- ----------------------------
INSERT INTO `tenant` VALUES (1, 'ykf-10001', '10001', 'jdbc:mysql://127.0.0.1:13306/ykf-10001', 'root', 'OFaLwY0Pu05nSGkrcXWEcOy0aUUu64HQGbo7+jzpuZQ9ErIZh8t3jeHRwYA1NAYX+Am6An5P5ntGuqmZcqLCbw==', 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbb7BZp7rZnpBjKLGmbWHX8A8xuOytwhGlUdmBZEIGiN7eGfrHDC/XZVkKDwTDVQ8kobbdD30EHu/Q8iex+u3kCAwEAAQ==');
INSERT INTO `tenant` VALUES (2, 'ykf-10002', '10002', 'jdbc:mysql://127.0.0.1:13306/ykf-10002', 'root', 'OFaLwY0Pu05nSGkrcXWEcOy0aUUu64HQGbo7+jzpuZQ9ErIZh8t3jeHRwYA1NAYX+Am6An5P5ntGuqmZcqLCbw==', 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbb7BZp7rZnpBjKLGmbWHX8A8xuOytwhGlUdmBZEIGiN7eGfrHDC/XZVkKDwTDVQ8kobbdD30EHu/Q8iex+u3kCAwEAAQ==');
INSERT INTO `tenant` VALUES (3, 'ykf-10003', '10003', 'jdbc:mysql://127.0.0.1:13306/ykf-10003', 'root', 'OFaLwY0Pu05nSGkrcXWEcOy0aUUu64HQGbo7+jzpuZQ9ErIZh8t3jeHRwYA1NAYX+Am6An5P5ntGuqmZcqLCbw==', 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbb7BZp7rZnpBjKLGmbWHX8A8xuOytwhGlUdmBZEIGiN7eGfrHDC/XZVkKDwTDVQ8kobbdD30EHu/Q8iex+u3kCAwEAAQ==');
INSERT INTO `tenant` VALUES (4, 'ykf-10004', '10004', 'jdbc:mysql://127.0.0.1:13306/ykf-10004', 'root', 'OFaLwY0Pu05nSGkrcXWEcOy0aUUu64HQGbo7+jzpuZQ9ErIZh8t3jeHRwYA1NAYX+Am6An5P5ntGuqmZcqLCbw==', 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbb7BZp7rZnpBjKLGmbWHX8A8xuOytwhGlUdmBZEIGiN7eGfrHDC/XZVkKDwTDVQ8kobbdD30EHu/Q8iex+u3kCAwEAAQ==');
INSERT INTO `tenant` VALUES (5, 'ykf-10005', '10005', 'jdbc:mysql://127.0.0.1:13306/ykf-10005', 'root', 'OFaLwY0Pu05nSGkrcXWEcOy0aUUu64HQGbo7+jzpuZQ9ErIZh8t3jeHRwYA1NAYX+Am6An5P5ntGuqmZcqLCbw==', 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbb7BZp7rZnpBjKLGmbWHX8A8xuOytwhGlUdmBZEIGiN7eGfrHDC/XZVkKDwTDVQ8kobbdD30EHu/Q8iex+u3kCAwEAAQ==');
INSERT INTO `tenant` VALUES (6, 'ykf-10006', '10006', 'jdbc:mysql://127.0.0.1:13306/ykf-10006', 'root', 'OFaLwY0Pu05nSGkrcXWEcOy0aUUu64HQGbo7+jzpuZQ9ErIZh8t3jeHRwYA1NAYX+Am6An5P5ntGuqmZcqLCbw==', 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbb7BZp7rZnpBjKLGmbWHX8A8xuOytwhGlUdmBZEIGiN7eGfrHDC/XZVkKDwTDVQ8kobbdD30EHu/Q8iex+u3kCAwEAAQ==');

SET FOREIGN_KEY_CHECKS = 1;

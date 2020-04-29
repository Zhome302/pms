/*用户信息表*/
CREATE TABLE `I_USER` (
  `userID` varchar(30) NOT NULL,
  `passwordType` int(11) NOT NULL COMMENT '0-明文 1-md5 2-des3 7-SHA256',
  `password` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0-正常 1-暂停 2-注销',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modDate` datetime DEFAULT NULL,
  `operator` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
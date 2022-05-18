/*
 Navicat MySQL Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 18/05/2022 21:23:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `a_id` int(0) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `a_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `a_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `a_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13301202588', 'upload/1/admin/20220506111252013');
INSERT INTO `admin` VALUES (2, 'admin0', '4297f44b13955235245b2497399d7a93', '0000000', 'upload/1.png');
INSERT INTO `admin` VALUES (3, 'admin1', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `b_id` int(0) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `b_type` int(0) NULL DEFAULT NULL COMMENT '1豪宅 2电梯房 3普通房',
  PRIMARY KEY (`b_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, 'A栋', 1);
INSERT INTO `building` VALUES (2, 'B栋', 2);
INSERT INTO `building` VALUES (3, 'C栋', 3);
INSERT INTO `building` VALUES (4, 'D栋', 3);
INSERT INTO `building` VALUES (5, 'E栋', 2);
INSERT INTO `building` VALUES (6, 'F栋', 2);
INSERT INTO `building` VALUES (7, 'G栋', 3);
INSERT INTO `building` VALUES (8, 'H栋', 2);
INSERT INTO `building` VALUES (10, 'J栋', 2);
INSERT INTO `building` VALUES (11, 'K栋', 2);
INSERT INTO `building` VALUES (12, 'L栋', 2);
INSERT INTO `building` VALUES (13, 'M栋', 1);

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `c_id` int(0) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `c_type` int(0) NULL DEFAULT NULL COMMENT '0地下 1室内',
  `c_state` int(0) NULL DEFAULT NULL COMMENT '0无主 1有主',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1, '车位1', 0, 1);
INSERT INTO `car` VALUES (2, '车位2', 1, 0);
INSERT INTO `car` VALUES (3, '车位3', 0, 0);
INSERT INTO `car` VALUES (4, '车位4', 1, 0);
INSERT INTO `car` VALUES (5, '车位5', 0, 0);
INSERT INTO `car` VALUES (6, '车位6', 1, 0);
INSERT INTO `car` VALUES (7, '车位7', 0, 0);
INSERT INTO `car` VALUES (8, '车位8', 0, 0);
INSERT INTO `car` VALUES (9, '车位9', 1, 0);
INSERT INTO `car` VALUES (10, '车位10', 1, 0);
INSERT INTO `car` VALUES (11, '车位11', 0, 0);
INSERT INTO `car` VALUES (12, '车位12', 1, 0);
INSERT INTO `car` VALUES (13, '车位13', 1, 0);
INSERT INTO `car` VALUES (14, '车位14', 0, 0);

-- ----------------------------
-- Table structure for guestbook
-- ----------------------------
DROP TABLE IF EXISTS `guestbook`;
CREATE TABLE `guestbook`  (
  `g_id` int(0) NOT NULL AUTO_INCREMENT,
  `g_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `g_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `g_release_time` date NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`g_id`) USING BTREE,
  INDEX `user_guestbook`(`user_id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guestbook
-- ----------------------------
INSERT INTO `guestbook` VALUES (1, '好文章分享', '今天分享一片情感励志文章<p>美国漫画家罗素。迈尔斯系列漫画《女巫希尔达》而知名。在他的一集漫画中，绿衣女巫</p><p>希尔达正站在悬崖边远望。秃鹫吉劳德站在悬崖对面，向希尔达招手:到这边来吧，我们一</p><p>起玩!”</p>\r\n		<br><p>希尔达有些动心，然而看看脚下的峡谷，她犹豫了:“可是我跳不了这么远! \"吉劳德回答:</p><p>不要用消极思维打败自己。我正在写一本书，是关于积极思维的力量。我在书里证实，只</p><p>要你有正确的态度，就能做成任何事情!，</p>\r\n		<br><p>希尔达一边打量着他们之间的巨大鸿沟，一边思考着吉劳德的鼓动。吉劳德再次激励她:告</p><p>诉自己你做得到，行动起来!希尔达一十自信心极度膨胀，热血沸腾地喊道:好吧，我来了!</p><p>她后退几步，助跑，大步跃出。然后一直掉下去。</p>\r\n		<br><p>吉劳德镇静地站在悬崖边上，眼看着希尔达落入峡谷中，渐渐变得只有铅笔尖那么大。然后</p><p>他转身走开，自言自语道: .看来我应该在书里加一章，教你如何锻炼腿部肌肉。”</p>\r\n		<br><p>我们经常听说态度决定一切\"，但这并不意味着你能达到自己设立的任何目标。只有那些投入</p><p>时间锻炼腿部肌肉(以及其他能力)的人，才有可能实现自己的目标。相信自己做得到固然好，</p><p>但是不要忘记提高自己的能力。</p>', '2022-04-13', 1);
INSERT INTO `guestbook` VALUES (2, '心灵鸡汤', '不知你是否有这样的感触，随着时间的变换，身边交心的人越来越少。原以为可以相伴终\r\n		<p>身的知己，随着岁月的推移，走散在茫茫人海，原以为可以一生守候、矢志不渝的情，却</p>\r\n		<p>在时间的洪流中日渐生疏。</p>\r\n		<br>\r\n		<p>时光留不住，往事不可追，不知不觉中，你发现，随着年龄的增长，能够聊的来的人越来</p>\r\n		<p>越少，经得起相处的没有几个。</p>\r\n		<br>\r\n		<p>有些人只能陪你走平坦的岁月，却不能伴你度过风雨连天。</p>\r\n		<br>\r\n		<p>有些人只能在你的世界友情客串，却不能在你的生活倾情出演。有些人只能在你生命走过</p>\r\n		<p>一程有却不能与你一直相伴相依。</p>\r\n		<br>\r\n		<p>这些生命里的变数和意外，让你开始惶恐，开始埋怨起世事无常</p>\r\n		<br>\r\n		<p>但其实，很多时候，人与人之间之所以走散，不是因为世事难料，而是因为人心善变，在</p>\r\n		<p>人情与利益的改变下，生命中的那些泛泛之交，渐渐与你走上不同的道路，在人潮里分道</p>\r\n		<p>扬镳。</p>\r\n		<br>\r\n		<p>事实上，行走在这俗世红尘中，并不是每个人都能保持最初的模样，或许 有的人前一刻还</p>\r\n		<p>笑容满面的说非你不可，永不分离，下一秒便冷眼相待，冷漠疏远。有的人，前一阵子还</p>\r\n		<p>与你亲密无间，下一刻却背信弃义地把你利用。在经历了无数分分合合以后，你渐渐明白了</p>\r\n		<p>一个道理:真心褪去、人走茶凉，皆是人生常态，凭借自己的一己之力很改变一些人和事，</p>\r\n		<p>人与人之间，真的没有所谓的永恒。</p>\r\n		<br>\r\n		<p>时间总在走，人心总是在变。</p>\r\n		<br>\r\n		<p>生活在这个复杂而又难料的世界里，谁都猜不透人心，任凭你如何揣摩，也无济于事，要变</p>\r\n		<p>的心，怎样也阻止不了:这世上的感情，根本摸不透任凭你用心良苦，也无能为力，要走的人</p>\r\n		<p>，怎样也留不下。所以，当缘分过去，你不必执著，不必去遗撼人走茶凉，物是人非。只有</p>\r\n		<p>适时地放手、恰好地转身，看清已经变质的人心，放弃已经落幕的感情，才是淡定之人应有</p>\r\n		<p>的选择。</p>', '2022-04-13', 2);
INSERT INTO `guestbook` VALUES (3, '问题留言', '留言板测试1', '2022-04-13', 3);
INSERT INTO `guestbook` VALUES (4, '小学数学提问', '留言板测试1', '2022-04-13', 3);
INSERT INTO `guestbook` VALUES (5, '组队去春游', '留言板测试1', '2022-04-13', 4);
INSERT INTO `guestbook` VALUES (6, '王者找队友', '留言板测试1', '2022-04-13', 2);
INSERT INTO `guestbook` VALUES (7, '快递找回', '留言板测试1', '2022-04-13', 5);
INSERT INTO `guestbook` VALUES (8, '留言板测试1', '留言板测试1', '2022-04-13', 2);
INSERT INTO `guestbook` VALUES (9, '留言板测试1', '留言板测试1', '2022-04-13', 1);
INSERT INTO `guestbook` VALUES (10, '用户测试', '我是用户留言测试', '2022-05-17', 1);
INSERT INTO `guestbook` VALUES (11, '用户测试2', '我是用户留言测试2', '2022-05-17', 1);
INSERT INTO `guestbook` VALUES (12, '用户测试3', '我是用户留言测试2', '2022-05-17', 1);
INSERT INTO `guestbook` VALUES (13, '用户测试4', '我是用户留言测试2', '2022-05-17', 2);

-- ----------------------------
-- Table structure for maintain
-- ----------------------------
DROP TABLE IF EXISTS `maintain`;
CREATE TABLE `maintain`  (
  `m_id` int(0) NOT NULL AUTO_INCREMENT,
  `m_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `m_release_time` date NULL DEFAULT NULL,
  `m_result` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `m_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0未受理 1已受理',
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`m_id`) USING BTREE,
  INDEX `user_maintain`(`user_id`) USING BTREE,
  CONSTRAINT `user_id_bk` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintain
-- ----------------------------
INSERT INTO `maintain` VALUES (1, '维修1', '2022-04-13', '完美解决', '1', 1);
INSERT INTO `maintain` VALUES (2, '22', '2022-05-04', NULL, '0', 1);
INSERT INTO `maintain` VALUES (3, '维修', NULL, NULL, NULL, 1);
INSERT INTO `maintain` VALUES (4, '维修', NULL, NULL, NULL, 1);
INSERT INTO `maintain` VALUES (5, '维修', NULL, NULL, NULL, 1);
INSERT INTO `maintain` VALUES (6, '用户维修11', '2022-05-17', NULL, NULL, 1);
INSERT INTO `maintain` VALUES (12, '维修', NULL, NULL, NULL, 2);
INSERT INTO `maintain` VALUES (13, '维修', NULL, NULL, NULL, 2);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `n_id` int(0) NOT NULL AUTO_INCREMENT,
  `n_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `n_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `n_state` int(0) NULL DEFAULT NULL COMMENT '0未发布 1发布',
  `admin_pu_id` int(0) NULL DEFAULT NULL COMMENT '发布者',
  `admin_mo_id` int(0) NULL DEFAULT NULL COMMENT '修改者',
  `n_release_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `n_modife_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`n_id`) USING BTREE,
  INDEX `mo`(`admin_mo_id`) USING BTREE,
  INDEX `pu`(`admin_pu_id`) USING BTREE,
  CONSTRAINT `admin_mo_bk` FOREIGN KEY (`admin_mo_id`) REFERENCES `admin` (`a_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `admin_pu_bk` FOREIGN KEY (`admin_pu_id`) REFERENCES `admin` (`a_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '关于便民小区停车规范的公告', '为引导广大业主文明规范停车，创造良好停车秩序，给您及他人提供停车便利，便民物业服务中心特提示广大车友朋友们规范、安全停车应注意以下事项：</p><p>1、请不要冲闯出入口岗，不要占用他人固定地下停车位，临停车辆请您将车辆停靠在临时停车位上;</p><p>2、不在非停车位区域停车，不在各组团出入口，公共通道、消防通道上停车;</p><p>3、骑线、压线停车等于一车占两位，致使他人车辆无法停靠，属侵犯他人利益的行为，请您将车辆按照车位停放整齐;文明停车的通告由admin提供!</p><p>4、如您发现有车阻碍通行，或其他车辆违停现象，请您及时与服务中心取得联系，我们将尽快联系该业主移车。</p><p>5、车辆停靠完毕请及时关好门窗，车卡分离，以防物品丢失;</p><p>让我们携起手来，从我做起、从现在做起，倡导文明停车、规范停车，拒绝违停，共同创建和谐的社区!文明停车的通告由admin提供!</p><p>西安市便民物业服务有限公司</p><p>便民物业服务中心</p>', 1, 1, 2, '2022-04-13 17:14:31', '2022-04-14 17:14:52');
INSERT INTO `notice` VALUES (2, '便民小区关于增补业主委员会委员的公告', '便民小区第8届业主委员会自2021年08月成立以来，努力履行业主大会议赋予的责任，由于部分委员因个人身体或工作原因先后提出辞职。为维护全体业主的利益，经业主委员会讨论决定开展增补新委员工作。</p><p>根据《物业管理条例》相关规定，结合目前便民小区业委会实际情况，决定对便民小区业委会成员进行增补，为体现公开、公平、公正的原则，现就有关事项公告如下:</p><p>一.本次增补委员人数拟定4-5名。</p><p>二.业主委员会委员应当符合下列条件:</p><p>1、本物业管理区域内具有完全民事行为能力的业主;</p><p>2、遵守国家有关法律、法规;</p><p>3、遵守业主大会议事规则和管理规约，履行业主义务，未欠交物业服务费和其他相关费用;</p><p>4、热心公益事业，责任心强，公正廉洁，具有社会公信力;</p>\r\n<p>5、具有一定组织能力;</p><p>6、具备必要的工作时间;</p><p>7、必须是本物业区域内的常住户。</p><p>三.增补业主委员会委员候选人在业主委员会及便民社区指导下由业主自荐或推荐产生，资格审核由业主委员会负责。</p><p>四.增补业主委员会委员由业主代表会议表决通过。</p><p>五.自荐推荐报名时间: 2022年03月05日--05月04日</p><p>六.自荐推荐报名地点: 便民社区服务中心。<p>联系电话: 18800001111</p><p>便民小区特此公告</p><p>便民社区</p>', 1, 1, 2, '2022-02-25 11:26:12', '2022-05-18 00:00:00');
INSERT INTO `notice` VALUES (3, '关于便民小区业主委员会换届公告', '兹因本小区第一届业主委员会届期已满，按规定进行换届选举，根据《西安市物业管</p>	<p>理条例》和《西安市业主大会和业主委员会活动规则》等规定，本次业委会换届选举</p><p>工作是在便民街道办事处、区房管局主管部门、便民社区居委会的指导监督下，由小</p><p>区本届业委会成员.组成换届选举工作组并主持开展换届选举工作。为保证换届工作的</p><p>公开、公正和民主，并符合相关律法规定，由换届工作组根据相关规定拟出本次换届</p><p>工作草案，并报经区房管局主管部门、便民街道备案同意后向全体业主进行公示。以</p><p>便让全体业主知情,并同时监督和参与,以此确保业主的真实意愿能得到如实地反映。</p><p>能选出大多数业主满意和拥护并能真正代表和维护全体业主合法权益的下一届业委会。</p><p>本次公示内容包含:</p><p>一、公告。</p><p>二、业主大会的形式和会议内容(含召开业主大会的时间、地点)。</p><p>三、业主代表的产生办法。</p><p>四、业委会成员任职条件。</p><p>五、换届工作组成员名单公示。</p><p>便民小区业委会换届工作组</p><p>便民小区</p><p>2022年02月01日</p>', 1, 1, 2, '2022-01-25 11:25:40', '2022-05-18 00:00:00');
INSERT INTO `notice` VALUES (4, '关于便民小区公益电影观看公告', '为了丰富大家的业余文化生活，增进邻里之间的感情,我服务中心将于本周六晚在H层空中花园播放露天电影,希望广大业主/住户朋友们踊跃参加观看。电影名:《战狼》时间:5月22日晚6点30分地点:Y层空中花园西安市便民物业服务有限公司便民服务中心', 1, 1, NULL, '2022-05-18 17:15:17', NULL);
INSERT INTO `notice` VALUES (5, '关于便民小区停车规范的公告', '为引导广大业主文明规范停车，创造良好停车秩序，给您及他人提供停车便利，便民物业服务中心特提示广大车友朋友们规范、安全停车应注意以下事项：</p><p>1、请不要冲闯出入口岗，不要占用他人固定地下停车位，临停车辆请您将车辆停靠在临时停车位上;</p><p>2、不在非停车位区域停车，不在各组团出入口，公共通道、消防通道上停车;</p><p>3、骑线、压线停车等于一车占两位，致使他人车辆无法停靠，属侵犯他人利益的行为，请您将车辆按照车位停放整齐;文明停车的通告由admin提供!</p><p>4、如您发现有车阻碍通行，或其他车辆违停现象，请您及时与服务中心取得联系，我们将尽快联系该业主移车。</p><p>5、车辆停靠完毕请及时关好门窗，车卡分离，以防物品丢失;</p><p>让我们携起手来，从我做起、从现在做起，倡导文明停车、规范停车，拒绝违停，共同创建和谐的社区!文明停车的通告由admin提供!</p><p>西安市便民物业服务有限公司</p><p>便民物业服务中心</p>', 1, 1, 2, '2022-05-15 00:00:00', '2022-05-18 00:00:00');
INSERT INTO `notice` VALUES (6, '关于便民小区业主委员会换届公告', '兹因本小区第一届业主委员会届期已满，按规定进行换届选举，根据《西安市物业管</p>	<p>理条例》和《西安市业主大会和业主委员会活动规则》等规定，本次业委会换届选举</p><p>工作是在便民街道办事处、区房管局主管部门、便民社区居委会的指导监督下，由小</p><p>区本届业委会成员.组成换届选举工作组并主持开展换届选举工作。为保证换届工作的</p><p>公开、公正和民主，并符合相关律法规定，由换届工作组根据相关规定拟出本次换届</p><p>工作草案，并报经区房管局主管部门、便民街道备案同意后向全体业主进行公示。以</p><p>便让全体业主知情,并同时监督和参与,以此确保业主的真实意愿能得到如实地反映。</p><p>能选出大多数业主满意和拥护并能真正代表和维护全体业主合法权益的下一届业委会。</p><p>本次公示内容包含:</p><p>一、公告。</p><p>二、业主大会的形式和会议内容(含召开业主大会的时间、地点)。</p><p>三、业主代表的产生办法。</p><p>四、业委会成员任职条件。</p><p>五、换届工作组成员名单公示。</p><p>便民小区业委会换届工作组</p><p>便民小区</p><p>2022年02月01日</p>', 1, 1, 2, '2022-05-15 00:00:00', '2022-05-18 00:00:00');
INSERT INTO `notice` VALUES (7, '关于便民小区停车规范的公告', '为引导广大业主文明规范停车，创造良好停车秩序，给您及他人提供停车便利，便民物业服务中心特提示广大车友朋友们规范、安全停车应注意以下事项：</p><p>1、请不要冲闯出入口岗，不要占用他人固定地下停车位，临停车辆请您将车辆停靠在临时停车位上;</p><p>2、不在非停车位区域停车，不在各组团出入口，公共通道、消防通道上停车;</p><p>3、骑线、压线停车等于一车占两位，致使他人车辆无法停靠，属侵犯他人利益的行为，请您将车辆按照车位停放整齐;文明停车的通告由admin提供!</p><p>4、如您发现有车阻碍通行，或其他车辆违停现象，请您及时与服务中心取得联系，我们将尽快联系该业主移车。</p><p>5、车辆停靠完毕请及时关好门窗，车卡分离，以防物品丢失;</p><p>让我们携起手来，从我做起、从现在做起，倡导文明停车、规范停车，拒绝违停，共同创建和谐的社区!文明停车的通告由admin提供!</p><p>西安市便民物业服务有限公司</p><p>便民物业服务中心</p>', 1, 1, 2, '2022-05-15 00:00:00', '2022-05-18 00:00:00');
INSERT INTO `notice` VALUES (8, '关于便民小区业主委员会换届公告', '兹因本小区第一届业主委员会届期已满，按规定进行换届选举，根据《西安市物业管</p>	<p>理条例》和《西安市业主大会和业主委员会活动规则》等规定，本次业委会换届选举</p><p>工作是在便民街道办事处、区房管局主管部门、便民社区居委会的指导监督下，由小</p><p>区本届业委会成员.组成换届选举工作组并主持开展换届选举工作。为保证换届工作的</p><p>公开、公正和民主，并符合相关律法规定，由换届工作组根据相关规定拟出本次换届</p><p>工作草案，并报经区房管局主管部门、便民街道备案同意后向全体业主进行公示。以</p><p>便让全体业主知情,并同时监督和参与,以此确保业主的真实意愿能得到如实地反映。</p><p>能选出大多数业主满意和拥护并能真正代表和维护全体业主合法权益的下一届业委会。</p><p>本次公示内容包含:</p><p>一、公告。</p><p>二、业主大会的形式和会议内容(含召开业主大会的时间、地点)。</p><p>三、业主代表的产生办法。</p><p>四、业委会成员任职条件。</p><p>五、换届工作组成员名单公示。</p><p>便民小区业委会换届工作组</p><p>便民小区</p><p>2022年02月01日</p>', 1, 1, 2, '2022-05-15 00:00:00', '2022-05-18 00:00:00');
INSERT INTO `notice` VALUES (9, '便民小区关于增补业主委员会委员的公告', '便民小区第8届业主委员会自2021年08月成立以来，努力履行业主大会议赋予的责任，由于部分委员因个人身体或工作原因先后提出辞职。为维护全体业主的利益，经业主委员会讨论决定开展增补新委员工作。</p><p>根据《物业管理条例》相关规定，结合目前便民小区业委会实际情况，决定对便民小区业委会成员进行增补，为体现公开、公平、公正的原则，现就有关事项公告如下:</p><p>一.本次增补委员人数拟定4-5名。</p><p>二.业主委员会委员应当符合下列条件:</p><p>1、本物业管理区域内具有完全民事行为能力的业主;</p><p>2、遵守国家有关法律、法规;</p><p>3、遵守业主大会议事规则和管理规约，履行业主义务，未欠交物业服务费和其他相关费用;</p><p>4、热心公益事业，责任心强，公正廉洁，具有社会公信力;</p>\r\n<p>5、具有一定组织能力;</p><p>6、具备必要的工作时间;</p><p>7、必须是本物业区域内的常住户。</p><p>三.增补业主委员会委员候选人在业主委员会及便民社区指导下由业主自荐或推荐产生，资格审核由业主委员会负责。</p><p>四.增补业主委员会委员由业主代表会议表决通过。</p><p>五.自荐推荐报名时间: 2022年03月05日--05月04日</p><p>六.自荐推荐报名地点: 便民社区服务中心。<p>联系电话: 18800001111</p><p>便民小区特此公告</p><p>便民社区</p>', 1, 1, 2, '2022-05-15 00:00:00', '2022-05-18 00:00:00');
INSERT INTO `notice` VALUES (10, '关于便民小区公益电影观看公告', '为了丰富大家的业余文化生活，增进邻里之间的感情,我服务中心将于本周六晚在H层空中花园播放露天电影,希望广大业主/住户朋友们踊跃参加观看。电影名:《xxx》时间:5月22日晚6点30分地点:x层空中花园xx市xx物业服务有限公司xx服务中心', 1, 1, 2, '2022-05-15 00:00:00', '2022-05-18 00:00:00');

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay`  (
  `p_id` int(0) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay
-- ----------------------------
INSERT INTO `pay` VALUES (1, '水电费');
INSERT INTO `pay` VALUES (2, '车位费');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `r_id` int(0) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `r_area` float(50, 0) NULL DEFAULT NULL,
  `r_state` int(0) NULL DEFAULT NULL COMMENT '0无主 1有主',
  `unit_id` int(0) NOT NULL,
  PRIMARY KEY (`r_id`) USING BTREE,
  INDEX `unit`(`unit_id`) USING BTREE,
  CONSTRAINT `unit_room_bk` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`t_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, 'A1001', 150, 1, 1);
INSERT INTO `room` VALUES (2, 'A1002', 150, 0, 1);
INSERT INTO `room` VALUES (8, 'B1101', 100, 0, 12);
INSERT INTO `room` VALUES (9, 'B1102', 100, 0, 12);
INSERT INTO `room` VALUES (10, 'C1101', 95, 0, 13);
INSERT INTO `room` VALUES (11, 'C1102', 98, 0, 13);

-- ----------------------------
-- Table structure for unit
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit`  (
  `t_id` int(0) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `building_id` int(0) NOT NULL,
  PRIMARY KEY (`t_id`) USING BTREE,
  INDEX `build`(`building_id`) USING BTREE,
  CONSTRAINT `build_unit_bk` FOREIGN KEY (`building_id`) REFERENCES `building` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit
-- ----------------------------
INSERT INTO `unit` VALUES (1, 'A1', 1);
INSERT INTO `unit` VALUES (11, 'A2', 1);
INSERT INTO `unit` VALUES (12, 'B1', 2);
INSERT INTO `unit` VALUES (13, 'C1', 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(0) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `u_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `u_sex` int(0) NULL DEFAULT NULL COMMENT '1 男 0女',
  `u_phone` bigint(0) NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `u_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `room_id` int(0) NULL DEFAULT NULL,
  `pay_id` int(0) NULL DEFAULT NULL,
  `car_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', 'e10adc3949ba59abbe56e057f20f883e', 1, 18802200000, '娃娃', NULL, 1, NULL, 1);
INSERT INTO `user` VALUES (2, 'user2', '123123', 1, 18733336669, '小米', NULL, 0, NULL, 0);
INSERT INTO `user` VALUES (3, 'user3', '123123', 1, 10203024548, '红米', NULL, 0, NULL, 0);
INSERT INTO `user` VALUES (4, 'user5', '123456', 1, 18801012563, NULL, NULL, 0, NULL, 0);
INSERT INTO `user` VALUES (5, 'user10', '123123', 1, 18898563256, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_pay
-- ----------------------------
DROP TABLE IF EXISTS `user_pay`;
CREATE TABLE `user_pay`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `pay_id` int(0) NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_bk`(`user_id`) USING BTREE,
  INDEX `pay_bk`(`pay_id`) USING BTREE,
  CONSTRAINT `pay_bk` FOREIGN KEY (`pay_id`) REFERENCES `pay` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_bk` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_pay
-- ----------------------------
INSERT INTO `user_pay` VALUES (1, 1, 1, '20', '2022-05-13 21:21:00', 1);
INSERT INTO `user_pay` VALUES (2, 2, 2, '500', '2022-05-13 21:21:04', 1);
INSERT INTO `user_pay` VALUES (3, 3, 1, '100', '2022-05-13 21:21:31', 0);
INSERT INTO `user_pay` VALUES (6, 4, 2, '400', NULL, NULL);
INSERT INTO `user_pay` VALUES (8, 5, 1, '50', NULL, NULL);
INSERT INTO `user_pay` VALUES (9, 5, 2, '500', '2022-05-15 10:48:56', NULL);

SET FOREIGN_KEY_CHECKS = 1;

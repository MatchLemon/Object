drop database if exists news;

create database news default character set utf8;

use news;

create table users
(
	id			int					not null	auto_increment,
	user_name	varchar(30),
	password	varchar(30),
	
	constraint 	pk_user 			primary key(id)
);

create table topic
(
	id			int					not null	auto_increment,
	topic_name	varchar(80),
	
	constraint	pk_topic			primary key(id)
);

create table news
(
	id			int					not null	auto_increment,
	title		varchar(80),
	summary		varchar(500),
	content		text,
	image_url	varchar(255),
	author		varchar(30),
	create_date	datetime,
	modify_date	datetime,

	topic_id	int					not null,
	
	constraint	pk_news				primary key(id),
	constraint	fk_news_topic		foreign key(topic_id)	references topic(id)
);

create table comments
(
	id			int					not null	auto_increment,
	news_id		int					not null,
	content		varchar(3000),
	author		varchar(30),
	author_ip	varchar(30),
	create_date	datetime,
	
	constraint	pk_comments			primary key(id),
	constraint	fk_comments_news	foreign key(news_id)	references news(id)
);

insert into users(user_name, password) values ('admin', 'admin');

insert into topic(topic_name) values ('国内');
insert into topic(topic_name) values ('国际');
insert into topic(topic_name) values ('军事');
insert into topic(topic_name) values ('体育');
insert into topic(topic_name) values ('娱乐');
insert into topic(topic_name) values ('社会');
insert into topic(topic_name) values ('财经');
insert into topic(topic_name) values ('科技');
insert into topic(topic_name) values ('健康');
insert into topic(topic_name) values ('汽车');

insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','','author', '2009-1-1','2009-1-1',1);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港少女被杀藏尸垃圾站案：凶手被提堂受审','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','','author', '2009-1-1','2009-1-1',1);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('广州发生公交车追尾事故 已致10人受伤','今天下午13时30分许，一辆189路公交车在五羊新村站追尾了停站的89路公交车。据目击者，两车约有10名乘客不同程度受伤，其中有老者。现场随处可见车玻璃碎片和血迹。
','广州发生公交车追尾事故 已致10人受伤','','author', '2009-1-1','2009-1-1',1);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港一警察清障行动中晕倒命危 梁振英探望 ','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','','author', '2013-1-1','2009-1-1',1);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','','author', '2014-1-1','2009-1-1',1);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','','author', '2014-2-1','2009-1-1',1);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港少女被杀藏尸垃圾站案：凶手被提堂受审','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('广州发生公交车追尾事故 已致10人受伤','今天下午13时30分许，一辆189路公交车在五羊新村站追尾了停站的89路公交车。据目击者，两车约有10名乘客不同程度受伤，其中有老者。现场随处可见车玻璃碎片和血迹。
','广州发生公交车追尾事故 已致10人受伤','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港一警察清障行动中晕倒命危 梁振英探望 ','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','','author', '2013-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','','author', '2014-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','','author', '2014-2-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','','author', '2009-1-1','2009-1-1',3);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港少女被杀藏尸垃圾站案：凶手被提堂受审','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','','author', '2009-1-1','2009-1-1',3);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('广州发生公交车追尾事故 已致10人受伤','今天下午13时30分许，一辆189路公交车在五羊新村站追尾了停站的89路公交车。据目击者，两车约有10名乘客不同程度受伤，其中有老者。现场随处可见车玻璃碎片和血迹。
','广州发生公交车追尾事故 已致10人受伤','','author', '2009-1-1','2009-1-1',3);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港一警察清障行动中晕倒命危 梁振英探望 ','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','','author', '2013-1-1','2009-1-1',3);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','','author', '2014-1-1','2009-1-1',3);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','','author', '2014-2-1','2009-1-1',3);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港少女被杀藏尸垃圾站案：凶手被提堂受审','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('广州发生公交车追尾事故 已致10人受伤','今天下午13时30分许，一辆189路公交车在五羊新村站追尾了停站的89路公交车。据目击者，两车约有10名乘客不同程度受伤，其中有老者。现场随处可见车玻璃碎片和血迹。
','广州发生公交车追尾事故 已致10人受伤','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港一警察清障行动中晕倒命危 梁振英探望 ','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','','author', '2013-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','','author', '2014-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','','author', '2014-2-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港少女被杀藏尸垃圾站案：凶手被提堂受审','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('广州发生公交车追尾事故 已致10人受伤','今天下午13时30分许，一辆189路公交车在五羊新村站追尾了停站的89路公交车。据目击者，两车约有10名乘客不同程度受伤，其中有老者。现场随处可见车玻璃碎片和血迹。
','广州发生公交车追尾事故 已致10人受伤','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港一警察清障行动中晕倒命危 梁振英探望 ','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','','author', '2013-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','','author', '2014-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','','author', '2014-2-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','美国一名外交官辞职抗议美对阿富汗政策','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港少女被杀藏尸垃圾站案：凶手被提堂受审','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','香港旺角砵兰街垃圾站尼龙袋私影少女藏尸案，涉案疑凶昨(11日)被押送到九龙城裁判法院提堂，被控以一项谋杀罪，暂时毋须答辩，案件押后至明年4月2日','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('广州发生公交车追尾事故 已致10人受伤','今天下午13时30分许，一辆189路公交车在五羊新村站追尾了停站的89路公交车。据目击者，两车约有10名乘客不同程度受伤，其中有老者。现场随处可见车玻璃碎片和血迹。
','广州发生公交车追尾事故 已致10人受伤','','author', '2009-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('香港一警察清障行动中晕倒命危 梁振英探望 ','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','11日晚，在金钟和中环的清障行动中，香港一名警察晕倒住院，经抢救后被送入深切治疗病房留医，情况危殆。香港特区行政长官梁振英与警务处处长曾伟雄昨晚分别赶到医院探望该名警长','','author', '2013-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','太原起飞客机因故障迫降榆林机场 无人伤亡 ','','author', '2014-1-1','2009-1-1',2);
insert into news(title,	summary, content, image_url, author, create_date, modify_date, topic_id)
values ('媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','媒体批刘铁男腐败：儿子前台挣钱 老子后台办事 ','','author', '2014-2-1','2009-1-1',2);


为了保证WebDriver进程和java程序一同关闭，如果用kill命令，要杀掉进程树；如果是程序自然退出，WebDriver进程会自动关闭
	windows：taskkill /PID 1234 /T /F
			taskkill /IM chromedriver.exe /T /F
	linux：kill PID
	
java执行后台命令
	nohup java -Djava.ext.dirs=lib -cp lib/spider.worker-0.0.1.jar com.lenovo.spider.SpiderContext > /dev/null &
	
java打包命令
	assembly:assembly
	
mysql合并表sql
	/*先备份子表*/
	rename table member_info to member_info_temp;
	rename table stockholder_info to stockholder_info_temp;
	/*后备份公司表*/
	rename table company_info to company_info_temp;

	/*先创建公司表*/
	create table company_info like company_info_temp;
	/*后创建子表*/
	create table member_info like member_info_temp;
	create table stockholder_info like stockholder_info_temp;

	/*合并公司信息*/
	INSERT INTO company_info_1 (
		NAME,
		credit_code,
		type,
		company_site,
		legal_represent,
		regist_capital,
		business_term,
		establish_date,
		regist_department,
		approve_date,
		regist_date,
		belong_trade,
		belong_area,
		address,
		business_scope,
		email,
		phone,
		create_time,
		update_time
	) SELECT
		c1.NAME,
		c1.credit_code,
		c1.type,
		c1.company_site,
		c1.legal_represent,
		c1.regist_capital,
		c1.business_term,
		c1.establish_date,
		c1.regist_department,
		c1.approve_date,
		c1.regist_date,
		c1.belong_trade,
		c1.belong_area,
		c1.address,
		c1.business_scope,
		c1.email,
		c1.phone,
		c1.create_time,
		c1.update_time
	FROM
		company_info_temp c1
	left join company_info_1 c2 on c2. NAME = c1. NAME
	WHERE c2.name is null;

	/*合并成员信息*/
	INSERT IGNORE INTO member_info_1 (
		NAME,
		id_number,
		company_info_id,
		phone,
		mobile_phone,
		email,
		positional_title,
		create_time,
		update_time
	) SELECT
		m2. NAME,
		m2.id_number,
		c1.id company_info_id,
		m2.phone,
		m2.mobile_phone,
		m2.email,
		m2.positional_title,
		m2.create_time,
		m2.update_time
	FROM
		member_info_temp m2
	JOIN company_info_temp c2 ON c2.id = m2.company_info_id
	JOIN company_info_1 c1 ON c1. NAME = c2. NAME
	LEFT JOIN member_info_1 m1 ON m1. NAME = m2. NAME
	AND m1.company_info_id = c1.id
	WHERE
		m1. NAME IS NULL;
	

	/*合并股权信息*/
	INSERT IGNORE INTO stockholder_info_1 (
		stockholder_name,
		stockholder_type,
		company_info_id,
		credential_type,
		credential_number,
		stockholder_rate,
		capital_contribution,
		capital_contribution_date,
		create_time,
		update_time
	) SELECT
		s2.stockholder_name,
		s2.stockholder_type,
		c1.id company_info_id,
		s2.credential_type,
		s2.credential_number,
		s2.stockholder_rate,
		s2.capital_contribution,
		s2.capital_contribution_date,
		s2.create_time,
		s2.update_time
	FROM
		stockholder_info_temp s2
	JOIN company_info_temp c2 ON c2.id = s2.company_info_id
	JOIN company_info_1 c1 ON c1. NAME = c2. NAME
	LEFT JOIN stockholder_info_1 s1 ON s1.stockholder_name = s2.stockholder_name
	AND s1.company_info_id = c1.id
	WHERE
		s1.stockholder_name IS NULL;
	

	drop table member_info_temp;
	drop table stockholder_info_temp;
	/*最后再删除表*/
	drop table company_info_temp;
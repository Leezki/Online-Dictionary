# Online-Dictionary

1.	客户端：
1)	UI类：所有界面相关类全部放在UI package里面，共有主界面（theFrame），登录界面（theLogin），注册界面（theRegister），好友界面（theFriend），信箱界面（theEmail）五个类，负责与用户进行交互。
2)	Client类：负责客户端的后台处理工作。具体负责网络编程、向服务器发送指令、接收服务器传来的信息，解析信息并反应到界面上。
3)	数据封装：客户端所有的数据都存放在Data package下面，Client和UI相关类都可以直接对数据进行存取，删改等操作。
2.	服务器端：
1)	Server类：负责服务器端的运行逻辑。具体负责接收客户端发来的指令，向数据库发送指令，接收数据库传回的数据，进行预处理等。
2)	dictionaryDB类：负责与数据库的交互。与数据库建立连接，对数据库进行查询，增加，删除，修改等操作。

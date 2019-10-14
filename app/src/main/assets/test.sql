
-- mysql测试

CREATE DATABASE IF NOT EXISTS test_db;
USE test_db;

drop table if exists test_table;
-- 测试表,本次测试表字段统一使用下划线分割字段
create table test_table (
  id bigint primary key auto_increment,
  test_id1 int,
  test_id2 smallint,
  test_id3 bigint,
  test_money1 float,
  test_money2 double,
  test_name varchar(32),
  head_base64 text,
  test_age1 int,
   test_age2 smallint,
   test_age3 bigint,
   price1 float,
   price2 double,
  test_img blob,
  birth_date date,
  create_time timestamp,
  test_my_remark varchar(255)
) ;

-- 把16转换为对应的blob数据
-- insert into test_table(test_img) values(UNHEX(HEX('89504e470d0a1a0a0000000d4948445200000090000000900806000000e746e2b80000000473424954080808087c08648800001a8449444154789ced9d79901cd77ddf3fafefb967f6c42e16f749122078583c448904c5f84a5476d93150394ba259962a91ff7055e44a4a558ac11c8e5dc58a25277459941dd9b2ca7111b112bba444969d00b4ac88940952a4181284480004815d2c16d86beebe5efe98d9c562b76777ba678f59b03f558bd9ed79bf7e0fd3df79fddeeffddeaf2126262626262626262626262626262626262626262626262626e6f644acd999a5143cfdb4e0ce3b97ade358f3f564c46a36b3fdb105bf7762bfaced9b6f4a7eedd72442c80855ac33528aa3a74e689c38a16c7453621671e28472f4d4090d2957b5d358b5931d3d75423bfdf80977eeef9d5ff98aa55ab25f733c4de86e4bf5e7ad3c790b2e4e4f47aa778b9507e06a2d9afdce7c9e5a2daabdc5c17c9eabb569a66bb508d6163bf3792e4e4f5323bc7ddeb2d862e5393b3d0d01f6d2d184ababae571313179f7c72bec0e26bd509da6a9ce4d8f3cfab271f3fee1e3d7542bb323afc4f84e018d8079162506a1852aa2d05a4480fc537f1a51aa96e450121886c2f3c15173b92bd263c5ce9e1794a247ba9495ce9810abe1bdedef5145ce9a1080fd70fb0d7a450a56baba61cdfffc7cf9d9592935b8747bf76faf1132ecf3faf72fcb817bad24574dc031d7bfe79f5e4f1e3dec35fffea638e2f7f173838ffa65cf9b6db6725c81a06e7676722d53f9c4c23045c299722d9efcae428393613b56a685b4d087666735cab549875ecd0f60955636b3acd955289aa17be43c8ea0603c92417676771a5bf7c61317fa9cfe2f8ffece57ff8e4e9b96b17bae20574d403cd35e09f9ffa8b7f3c5a2b7dcd951224ae4422a454a4102b0a743895a6cf4a5076a3f5a8bbb2398410b86d8835883db93c53f53a8a127ed8a6290a7b73050c55e3460401a6349dbdb90220284610608f69b12393a5ee79d87e6b010929a514c20710421c94529efae93ff9a34f9c3c7efcab9d8a28b2808e9e3aa19d7cfcb8fbd0c9af1c1dad95be76a13883e3790ea0876a8050d051b8301b6d0c53304c142122db6f4b66b8562b47b23715951da92c63e51257cac5d0f605c3624b22c5e5d22c93f5f063a07a324dc1b0b8549c5da9071340e31b227150842e6dfb0f3ffca5dfbe7af2f8f16f7732268a262029c56921dcfbbff429dd45f95d574a1ccf735cdf0f251e00d7f7f1a4c45de61bb4acbd9428cdf344a15177b4fa5521f07c1fb7f9131657fa4dfb68f5cfd93be1ead771715055ddd6fc67478e1dbbfbf4e327aa4829a24cf5234db78f9e7e5a05c48c75f73f457000894bc89e27660311e808e12a86b177e7471ff82540dcffdca7237526910474fa057c405154ed18806413f8a8621621514d0355d77e0e50cffcd554a42e3cbcea9a5d5ddf2ffe620ad19871092963c7e12643fa28425541d7f6a63ffad142e9e4c9eb516e63e12ffcd34f0b40b1760d6f918801a4a49dd9564c77210402215075bd37b96fc730a070fc78683d44e939144028b663088111c13ea60b98f77a08a10bc7b50041a1b02e020210be5d56e2c1cfe647e223ddba4244a77278018d8e0a4091f57ab4b58398eec3f31440616a2ab488220f7ea5e7c7e39edb04d9c10a7d3c7b8ae988c863a0556d454c37b04e63a0989805c4028ae988d09ee8fb813340524fd26725c91a06c3c9349a50222d08ee4c67194c26d997eb096d0bb02d9541016622da6f4da53055153f423888a12a0ca552547d8fa4167e29306b180ca552ecb6f3f4da89d0f603892443a9147b7305ea5ec8880c29292412d41c0f5db300b80bf87f21db10399c236540ce32c9683a7d8904ba50f0225c84c164925e2bc1482a13a91d03560221042376f8781a6804b4a942c189207e5d51e8b512545d175d84efccd3ba4eaf9560389926a387f7c9f69a26bd5682adc9f4b2f14041482479d3e2fa4c09ab8365f0d0023ad37c9d2857a84e4f91354ccaaecb85d9e9483dd0be5c0f23a90ca746df0b6d0b30e36c41118297af8d45b2f7a5e46ab5ccdbd33742db9aaa8a949237a76f448b07322d6aaecb99ebe34cd6c307a40da7d2dc65db7ce7ea656a21231aa584fe640ab35ca4586dc42285ed7d201e03c574482ca0988e880514d311b180623a2216504c47c4028ae9885840311d110b28a6236201c574442ca0988e880514d311b180623a6255f203ad27070bbd3c75e030c3e90c2f8d8ff2d6e424534ef8c404ddc24022c9bfbaf701b6a573bc35759d2f9f7d9db188a96a3682c8f140793dc9602a434637d895cd5130cc482956b6a5320c5809669c2d2b96cde9269fffb187194aa601b8a77780f3b3d37ce3bd7722c5f300eccde5e8314d527af898065d51d89dcba10a85c1642ab4fd9e4c8e27ef38ccd66628cb91be010ef70ef0cc6bdf6f2bbca4df4cb0339ba5e43ad4fdf0f140792b41d183f7348b09d6391e081a398b1a3f024588e8bb149bf62bf1d8f0c8bc78001421d89b2bf0b33bf7335a2e33562d47a8bfd9f6089b6b1bffe768f67b33399ebae3c8bc78a011947ca8a78f43f95e5e9bbabee2394407f5034d9bcec2db23c7034d3b15dc52916cb3e7891a0f3493eb61c4b6db8ae779b03fb897da99cdf1f3bbf7f3b997fe9ab14ab8ee3fa5ea1dc503a5553d743cd0be7c0fc7f7dec1483a3888eebd72b1adcf633895c6f37dce4c5c8d1e0f542d52743f20f140a7c6dec70ee8aa05f0e0e030bff9d0630c2492ebdfb010eccae679e6e1c7b9a3d01bf8fef55a955726c6d7b955d1d954027a73f23affe9f53381e303017c6860882f3cf20405d38a747e41a35b5785405314f4053f9aa2a0366f15513bfd917496673ff2e3eccf1502df2fbb0efffafb7f13293a71a3d874b3b0df3bfb3a8a10fcf2e1fbd103f21adedb37c8171e7982cf7ce72f29b59177d05014f6650b1cccf7b03f576047264bceb448eb0619dd206b18ccda3645c7a668d799b5eb5c28cef0a3e9692e9666d095f676786f49a6f8bda33fc58e7436f0fd9263f32f5f7c217268ef46b1e90404f0e5b3afe34bc92f1fbe1f535d7a011f1818e28b8f3cc167bf778aa945b9070582917486870686786264070f0e0ea32bea7cef134450c0bb2f253e12c7f7f9db6b637cebd2795ebc36c6d5726949c689ada90cfff9237f6759f17cfefb7fc3a92b9b4b3cb0490524a5e40fcebd8122049f39741f4680881edeb295df7ce8313ef7d25f73bd5625a31b3c3ab48dbfbf673f877afa49697aa499cb1c7333304d557874681b8f6c19a1ecd8bc7a7d9c93ef9ee3ff8e5fa6eaba6ccfe478e6e1a31c6c31e6a9b80ebff1ca8b7cfbf2854d99ea64530a081a4935fff0dc1b0c25d3fcc29e03688b6e6702f8c8d008ffee814779edfa383fb97d37bb32b940b1ad06aa10640d93c786b7f3e0e030efcc4cf117ef5fe089919d1ceee90fb429bb0efffdfc395e18bb1cd98fb5d16c5a0101d43d8f6f5dbe40ce34f8d8d69d4b6e6702c1a3c3db786c78dbbab6cb52350ef5f473a885700066ed3acfbef10ae395caa6150f6cb25958108eeff3f50b3fe2f7df7a1d3b60776637668198b5ebfcfb575ee47f5e3abfa9c503b78180006a9ec7736ffd80ffface9b1bdd94b6f8c2eb2ff3cdf7de89b493b7dbb82d042468ecf2dc1a717bf47a735fdf20bd56f8bdf0ddc8a61e0341433cbb33397ef5c8031cee6d3de6e8263ebe732f5bd3197eebf59737ba291db3e97ba0bdd93cbf72f78f6d1af1cc716fdf20fff1c31f637736b7d14de9884d2da0fbfab7f09943f7918fb874b1d1f459093e71f030070bd152d3740391e381fa5349867279329ac19e5c9e6dc94ca441e1d6548a3e2b117a36b23d9de197ee3c427f972f9eae44c1b4f80f0f3eca97df7c8df74be122097a2d93ede92c20021799974322c95b1637c6c6792fb101f1408e0d25db464898aad7b9562be3fae10564aa2aaa50b81a229667473acb5377debde9c533c74022c553771ce1d7cfbcc8a5f26cdb763e92ac61325e2b474a30654b9faae78013b2c10be82c1ea85aa1e679288a12391ec8978df5a476e371929ac6afdef3210612e12300bb99c1648a637b0ff02bdffd2b2a6d3e7cafe8d4b1149573d393d1e281ec1466adfcc1890702f8f49df7f0f0e0d6ae74107682001ed9b2954fdf71cf463725149b4a401f191ae193070e77b408dacd2842f0c983877964cbc84637a56d368d8032bac1678f3cb0668ba1dd82a1aa7cf6c887c84448dab9116c0a012942707ccf010ee4c34f776dcfe3fcf4142f8d5d66bc5c5af3b5275f4ac6cb255e1abbccf9e9a9c0f5b9953858e8e51776ee43d9044b1d9bc2133d92cef0e4c1bb43dbcdd46bfc9befbdc0ff3a7f0ed7f7c918269fb9e7019ebcfbbe35194349e00fde7895677ff0b714ed3a9aa2f07777efe3f30f3d462ea4afeaa943f7f097ef5fe072b5024af7deb2bbbe075285e013fb0f455a3bfad26b2ff38d77df9e9f1d16ed3a5f7ce57b7cf7caa5d56e26002f8dbecf175f7989a25d07c0933edf78f71ccfbd167ec9a2d74af2c98377235c17223e50783de87a010da732fcf8b69d916c4fbf7f01b9e83650755dbe7b796d4247bf7be51255b7e154991be74b24a722d6f7133bf7309c4ae33b4ed78aa8eb05f4f1edbbe9b7a2390c932d9277a72324f50e53dfe24962945daf00fd89241fdfb50f24f84e77f6445d2da0fe44929fdcbe3bb2fdcfee39b064d7446f22c9e33b7675dab4401edfbe8bfee4ad62d7158d9fd97320f2397f7ad73e06922990b22b45d4d583e8bb0a7dec6db187aa1d8e1d3844d9b1f9b377dfa664db0ca7b37ceaeefbb9a37760155b7993033d7dfcdb0f7f8ce7de38c368b148da30f9993dfb39b6ffaec8e7dc9b2f70576f3fd72a6540e2bb2e8aa641c096a68da06b052468ec85573b701a5a9ac6a78e7c889fdb772765c7a13791083d1b0a83003eb66337f76f19e646ad4a4ad3e94fa63a727caa42e1b1ad3b387df96263d7869448d74574899fa86b0554302deeed5b3963c74a284230984aaf5c7015c999d6aa0af5dec1210a5682c95a63c7aa94125c072937fea9c71d084847130a5a731bb0a9a8917a0b436d6c1d5ebca3627fbeb719aa10b32393e540a197330b122e08049a27d184683cf425a494a46c7ef642d0890c22c7030da7920ce6f364549dbdb9023b92593c197e8037d48c075a3cdd3e3abc8d84d6b51de4ba92d074fede8e3d24e62604cd2f6ac1b0d89eca2006b6e2483f94735422c95a16d73d858b1b110f346d5710e53255ddc0d034c6caa548e11c55dfa3eababcb9289ce3e33bf6446dda6d49c6b4787b7a92851b950692490482b337c6b115b1d47fb01c52d29b4ce2d4cb943ac8f016391ea8e23828761d09dca855b9522e46125052d3d185724b7e1d550886d679dcd2ed0ca7338c57cab7447d4a2405d362b45ca2e67b084d6d5b4452425d4a4cc7c669c61fdd36f140fd8924056b73c639af153d5682fe4569f41af9c5e65cde12e97a0d65ac235d29a09c619150e3f1cf42129a1e30b35bd4db4889f4d657445d29a084a6b59d77e78382ae28b74e2a826e5582751751570ac852b5c0e4511f647445c59aeb955b89678e751451575e254b5563012d4257142c4d5b593c730724eb22a2ae1c689c9dbacebf78e1dbc836fc4a3bd3591404e74b3391ea3a98eb61c6b60353042fbd56b71ed0148583b902a3950a93763b790d6fb54f693abbb3392e146729b9f68252c133a9b353013b57966b635344426d7f761696ae14d0d56a85b16211bf5e5ff11b74a46f0005c1abd7a365362d0e8d70ad5ae1473353b71c5f493cd0885f2e0f8d706e7a922b0bb3cbb7bc56e2965ff386c554ff203fb83ece6433155fcb149eedf63c8b59631175ed7d42682a8a69aed937a75149f0b9db114f4bfb36c5135c6295c5b3e0f05addceba5640d01491b54622baadc4b372b500d2f7575d445d2d2000a1ae81883a11cfdcf31dda28bae48d76ca2cdfa0a5455b3daea0c529575b445d2f20586511752a9e60eb958faf917802ad830e2e38df6a8a6853080856494481b602b1e4f8fa88a7e5b34e5661ccb3d2f9a42f6115a289228773e8e824348d84aa91d2740a86851b219c236b18a475bdedc71348c3c2abd7a0990924ad190821c81be6f2862d04903674aa9e41de58a6fe16b686aa92d275328641de6d61bf4c8f97314c529a4ed630175dca453681fa16647493a4a693374d6a9eb6c2d8e8d6372590d5741455456fca605dc339fa53490633d9f978a02d56aaa378a05a9b1929003ccfc5add591d267673ad748f8bdece6bb561751b02793a3d74c9033cc16c5440b0d3402e97667b2988ada780c95587c8a25076e399ed274766772080425d70e1e482f73db2a181623e9348eefe3b4f8ec83ab1748d98c07b27dd289750c289b63a25cc12d161b8f011082cba5d948f98176db79869369ce84f4e348d7c5afd7717d1f45087e786322b8e00a631e535599a855b9303bddbe7df390a1a858aaca3b33d34bf31bb5d2ce82a359c34420f8e1e404b3cdcd882d8a061e1848a6707c9fd76e5c5b9a1f688531979492de640a512b52aa864b0db390c8f1400e0e15d74153148a8ecd64bd16291ea8d74e90d18d484fa891488aae832259f24c0ca0ad0173c97128daf5f6ed171c32149592e330bbd8becda9ba044aaecdac5d9f7724b63ec7d2935a9a46c97398aad796e6076a31de9baf5b82a66918b687c36d160fd42e4253d12c2b78ef78bbb32d31ffcfcaf6adae8958a10cadfd3c6dddb6c20c986145f1b42c1281ae5cca0883a2698d607e216e4e4dd7cb49b8f83615d649185cb88d8a5bb5a7bd99e2c28faa5336750f3487d0b49b53fc4de5615e61b6b546e2594d6e0b01c1f27ea28df230afe5da563b45d75a3c701b09089a22328d5b3ea9f6c4d3ea842d6b5af47b8068d74b3c2ddf68433c02e830f0f3b61210dc14911041fe9b4ec6222dde08d4c37a89a7f5d07ca56a97ad2b04b79d80a02122b1a8276ae995696b2c12f04670c7b38eb7ad0ec73cab743fbb2d05044d11197322ea8601f34af584bd6dad5c78adc503b7b18060b18816bfb989c5d3899f679547d2b7b580604e44faad1fdce20f71994eaa1df174d4c3b5baa01d08208c783a95d36d2f205824a250dfc036c4d35200ed88a7e5d838c4396f55ff7af53c737c200404ad06d6cb5a04fe7a6b894e6f5b6dde465bfaa836e6b6b590c8f14049bd11c792d50d7a4c8b7a321d291e682091a4d732198e984ca1cf4ca008d1b6bdf47ca46d375cf9027acc04be8492632f28d5de375a57540a86c54032894436addabf6d6574b3699f6aeef90aaca865fd03891405d3622895a21ef8b8a7e02e4e0048494f22855baf6f4c3c50de4832984a91517576a4b31474aba378a099babd72e1007665b22842e0b519092001e97bb8b60d1246526932bad14870b5f8f35e6eaa2c40130a23a9340a82826905f74801e2993b94d2f4f9789e9217fccca5604d358ef6981623e90c65c7c5091250ab651d6ee6079aa8d4d7371e682e9c63b45ca132334d5637a9fb1e978ab33811c239f6e60a6c4da6f9ced5cba16d018aae832a046726ae86b293be8f6cf63a13b50aefcc4c11f6b665a82a42087e343dc95825e079672bf42879d3c46ec6f32c092711cb34a2797c2895a2e4b87cefda955b03f296b56d2025f42793689559a6aad1c3393a588d77707d1f57fad8be4fd57323c503d53d0fdbf7433fef6a0edbf7508488642f150547fad43d8fdac280ac36c73c3e12c7f7a807b5bf8da97acdd316d4bf5000ed3909ebbe87e37bd45cb74dfb9b480935dfc39412881e50f681194407211405d5306e0daa0f39605ef5789e2ef130b7cb075a40008aa2a2cc4ff183cbb48ee7d9c41ee6e53cf421f8c00b0840282a420fce7cb1ec543dc0afb3c281650f47f6308b566f84a9271ab1809a0845412c4a9fb2aef13c1d85a16e8c78a01301f9aa58a66fdf942c14d1e658180de9590fea61a50019fd8164e10574ee9c04705dd7939268ce9b2e46284ae35914ed0e703b144f6b4fcdf2d52e77cee0b2c1857dcf733cc76d4c41cf9f0f1d291d5e40fdfd12a0f4fd5727f0bc1b00627196f0cdce7c4fb4e0585bdff42e9d6d05979548895bad4e97df387b0d804c661d0474f2a4646444545e7db52e5df7ddb9a6843e4fb7b3704cd4aae7118b0f04b0ac78961f85afa1784008e9bb2ede6cf972fddd77a71919119c3e1dda9117690c3478678f02605f19fd96f4fdd5980d7627413d11b4ffffedce9e677e4f8f33334be9ead8ff0664ffde5ca4e8e8280292e3e72b2ee08ffff6ef7cdd9d9e7e4f4aa9226474776637a388667ab8e6df016b5bedf983e81ef128c2c573557be2fae8db5f3bf955c09fb858768990ae23da2cec9d77fcccfe61b53e315b9b7ce9d5dfa84d4e818f8690ee7a674a5f1714e55611cd13223f4f2b3a08c968e90a5c493c08cdb93cc6f46b6f3e63dfb851caec1f56b97831fcf3c989be16268be7466bf4f458efff973ffa3ffdb9ecafdbdb863e277a0a1abaee351bdb9eab534a64633c17b12572e14b787364a30d2bd90b0514d1c83548231644cae6b3bb16da0b82bfc7422c392e69d8cba67dcb1da301b68d13dcfcec6ed61f585622844408705ccdbe324af5ed73cf5c7bfe4fbf99e8e9a1786eb4d6a2d52b12454012f00197c9c9aa99cb99affcd6b37fbcfb1f1dbf9e3e74f0b35aa130ace7b2a8567bc15b854482bc652d790e44bbe4ad463c50747b0b5bfaf4db6dda4bbf99b0b2b11a9fb52c7a92496c7c5a0fa4838fe70c93ac65d19b4ca1053dda4accff13484f2245d6b2e84f26a9cd877304f749bee3e04c4d531bbf3656fde15b5fb8f6dffec737cd5cceab4e4e5668aca6fa441051d4e1afa0213e13481b994cde2e16f5444f4f7acff19fff07c9a18147b5747a584b585945377489d7b2610533414637b9546a915e6505b62452082118ab94562e1cc0b6749e8a6b73a35669db467a12e97b280846b259a6aa558a4eab789ed6e3234b53194ca6b9562d537503ee2001038c85674b1b06bd668a2ba519dc806b2fa422a4ef394ea532ebce16472b57c6bef3ee9ffef99f5427278b4626e3d8c5e2345002ea3444b46e0282c67f4f071240cae84d67ed1b2555d7750d50d49e9ebcb96b7b9fa26a1a7e6b7fa3aa18e82ad49c683e4943693c6adb5ea68ee5b07403df8b68ef35ec1ddfc35b9c9fa71d54154b57a9395ee36461cd69d8975bdaabf8527af50b9726bcc9c919c0731cc7357ad39e7da3340b94812ae0d0e88142d389800437456401494c33692475cb2edb0ab68daeebf15a5b17e0388e8f6160a40cdfae3835eaf50a50016adc144fa43150a71e9c85223268dcd24c40c7340da454308c65fd0b46f335ea9ac866b63716fcde89fdb2b6b6ed218447bdeed0104bbdf963d3a17860755c80732252698c8b341a82529bc75b795062d616b9e0d5a7718f736988c66dfedd917860f52eeadc7994453f0ba7f2b180d69785029a13d1c29f856522b3da1775b15862d17407b2c56bc7acf5058e05d41ddc86cb033131313131313131313131313131313131ebc9ff07cfa5702d3fd0aa390000000049454e44ae426082')))

INSERT INTO test_table (test_id1, test_id2, test_id3, test_money1, test_money2, test_name, head_base64, test_age1, test_age2, test_age3, price1, price2, birth_date, create_time, test_my_remark) VALUES (1, 2, 3, 9.99, 1999.99, '李四', '1225dfdfdfdfd', 20, 21, 23, 9.99, 1999.99, '2019-12-12', '2019-07-15 16:32:29', '我会唱跳rap,篮球');
update test_table set test_img = UNHEX(HEX('89504e470d0a1a0a0000000d4948445200000090000000900806000000e746e2b80000000473424954080808087c08648800001a8449444154789ced9d79901cd77ddf3fafefb967f6c42e16f749122078583c448904c5f84a5476d93150394ba259962a91ff7055e44a4a558ac11c8e5dc58a25277459941dd9b2ca7111b112bba444969d00b4ac88940952a4181284480004815d2c16d86beebe5efe98d9c562b76777ba678f59b03f558bd9ed79bf7e0fd3df79fddeeffddeaf2126262626262626262626262626262626262626262626262626e6f644acd999a5143cfdb4e0ce3b97ade358f3f564c46a36b3fdb105bf7762bfaced9b6f4a7eedd72442c80855ac33528aa3a74e689c38a16c7453621671e28472f4d4090d2957b5d358b5931d3d75423bfdf80977eeef9d5ff98aa55ab25f733c4de86e4bf5e7ad3c790b2e4e4f47aa778b9507e06a2d9afdce7c9e5a2daabdc5c17c9eabb569a66bb508d6163bf3792e4e4f5323bc7ddeb2d862e5393b3d0d01f6d2d184ababae571313179f7c72bec0e26bd509da6a9ce4d8f3cfab271f3fee1e3d7542bb323afc4f84e018d8079162506a1852aa2d05a4480fc537f1a51aa96e450121886c2f3c15173b92bd263c5ce9e1794a247ba9495ce9810abe1bdedef5145ce9a1080fd70fb0d7a450a56baba61cdfffc7cf9d9592935b8747bf76faf1132ecf3faf72fcb817bad24574dc031d7bfe79f5e4f1e3dec35fffea638e2f7f173838ffa65cf9b6db6725c81a06e7676722d53f9c4c23045c299722d9efcae428393613b56a685b4d087666735cab549875ecd0f60955636b3acd955289aa17be43c8ea0603c92417676771a5bf7c61317fa9cfe2f8ffece57ff8e4e9b96b17bae20574d403cd35e09f9ffa8b7f3c5a2b7dcd951224ae4422a454a4102b0a743895a6cf4a5076a3f5a8bbb2398410b86d8835883db93c53f53a8a127ed8a6290a7b73050c55e3460401a6349dbdb90220284610608f69b12393a5ee79d87e6b010929a514c20710421c94529efae93ff9a34f9c3c7efcab9d8a28b2808e9e3aa19d7cfcb8fbd0c9af1c1dad95be76a13883e3790ea0876a8050d051b8301b6d0c53304c142122db6f4b66b8562b47b23715951da92c63e51257cac5d0f605c3624b22c5e5d22c93f5f063a07a324dc1b0b8549c5da9071340e31b227150842e6dfb0f3ffca5dfbe7af2f8f16f7732268a262029c56921dcfbbff429dd45f95d574a1ccf735cdf0f251e00d7f7f1a4c45de61bb4acbd9428cdf344a15177b4fa5521f07c1fb7f9131657fa4dfb68f5cfd93be1ead771715055ddd6fc67478e1dbbfbf4e327aa4829a24cf5234db78f9e7e5a05c48c75f73f457000894bc89e27660311e808e12a86b177e7471ff82540dcffdca7237526910474fa057c405154ed18806413f8a8621621514d0355d77e0e50cffcd554a42e3cbcea9a5d5ddf2ffe620ad19871092963c7e12643fa28425541d7f6a63ffad142e9e4c9eb516e63e12ffcd34f0b40b1760d6f918801a4a49dd9564c77210402215075bd37b96fc730a070fc78683d44e939144028b663088111c13ea60b98f77a08a10bc7b50041a1b02e020210be5d56e2c1cfe647e223ddba4244a77278018d8e0a4091f57ab4b58398eec3f31440616a2ab488220f7ea5e7c7e39edb04d9c10a7d3c7b8ae988c863a0556d454c37b04e63a0989805c4028ae988d09ee8fb813340524fd26725c91a06c3c9349a50222d08ee4c67194c26d997eb096d0bb02d9541016622da6f4da53055153f423888a12a0ca552547d8fa4167e29306b180ca552ecb6f3f4da89d0f603892443a9147b7305ea5ec8880c29292412d41c0f5db300b80bf87f21db10399c236540ce32c9683a7d8904ba50f0225c84c164925e2bc1482a13a91d03560221042376f8781a6804b4a942c189207e5d51e8b512545d175d84efccd3ba4eaf9560389926a387f7c9f69a26bd5682adc9f4b2f14041482479d3e2fa4c09ab8365f0d0023ad37c9d2857a84e4f91354ccaaecb85d9e9483dd0be5c0f23a90ca746df0b6d0b30e36c41118297af8d45b2f7a5e46ab5ccdbd33742db9aaa8a949237a76f448b07322d6aaecb99ebe34cd6c307a40da7d2dc65db7ce7ea656a21231aa584fe640ab35ca4586dc42285ed7d201e03c574482ca0988e880514d311b180623a2216504c47c4028ae9885840311d110b28a6236201c574442ca0988e880514d311b180623a6255f203ad27070bbd3c75e030c3e90c2f8d8ff2d6e424534ef8c404ddc24022c9bfbaf701b6a573bc35759d2f9f7d9db188a96a3682c8f140793dc9602a434637d895cd5130cc482956b6a5320c5809669c2d2b96cde9269fffb187194aa601b8a77780f3b3d37ce3bd7722c5f300eccde5e8314d527af898065d51d89dcba10a85c1642ab4fd9e4c8e27ef38ccd66628cb91be010ef70ef0cc6bdf6f2bbca4df4cb0339ba5e43ad4fdf0f140792b41d183f7348b09d6391e081a398b1a3f024588e8bb149bf62bf1d8f0c8bc78001421d89b2bf0b33bf7335a2e33562d47a8bfd9f6089b6b1bffe768f67b33399ebae3c8bc78a011947ca8a78f43f95e5e9bbabee2394407f5034d9bcec2db23c7034d3b15dc52916cb3e7891a0f3493eb61c4b6db8ae779b03fb897da99cdf1f3bbf7f3b997fe9ab14ab8ee3fa5ea1dc503a5553d743cd0be7c0fc7f7dec1483a3888eebd72b1adcf633895c6f37dce4c5c8d1e0f542d52743f20f140a7c6dec70ee8aa05f0e0e030bff9d0630c2492ebdfb010eccae679e6e1c7b9a3d01bf8fef55a955726c6d7b955d1d954027a73f23affe9f53381e303017c6860882f3cf20405d38a747e41a35b5785405314f4053f9aa2a0366f15513bfd917496673ff2e3eccf1502df2fbb0efffafb7f13293a71a3d874b3b0df3bfb3a8a10fcf2e1fbd103f21adedb37c8171e7982cf7ce72f29b59177d05014f6650b1cccf7b03f576047264bceb448eb0619dd206b18ccda3645c7a668d799b5eb5c28cef0a3e9692e9666d095f676786f49a6f8bda33fc58e7436f0fd9263f32f5f7c217268ef46b1e90404f0e5b3afe34bc92f1fbe1f535d7a011f1818e28b8f3cc167bf778aa945b9070582917486870686786264070f0e0ea32bea7cef134450c0bb2f253e12c7f7f9db6b637cebd2795ebc36c6d5726949c689ada90cfff9237f6759f17cfefb7fc3a92b9b4b3cb0490524a5e40fcebd8122049f39741f4680881edeb295df7ce8313ef7d25f73bd5625a31b3c3ab48dbfbf673f877afa49697aa499cb1c7333304d557874681b8f6c19a1ecd8bc7a7d9c93ef9ee3ff8e5fa6eaba6ccfe478e6e1a31c6c31e6a9b80ebff1ca8b7cfbf2854d99ea64530a081a4935fff0dc1b0c25d3fcc29e03688b6e6702f8c8d008ffee814779edfa383fb97d37bb32b940b1ad06aa10640d93c786b7f3e0e030efcc4cf117ef5fe089919d1ceee90fb429bb0efffdfc395e18bb1cd98fb5d16c5a0101d43d8f6f5dbe40ce34f8d8d69d4b6e6702c1a3c3db786c78dbbab6cb52350ef5f473a885700066ed3acfbef10ae395caa6150f6cb25958108eeff3f50b3fe2f7df7a1d3b60776637668198b5ebfcfb575ee47f5e3abfa9c503b78180006a9ec7736ffd80ffface9b1bdd94b6f8c2eb2ff3cdf7de89b493b7dbb82d042468ecf2dc1a717bf47a735fdf20bd56f8bdf0ddc8a61e0341433cbb33397ef5c8031cee6d3de6e8263ebe732f5bd3197eebf59737ba291db3e97ba0bdd93cbf72f78f6d1af1cc716fdf20fff1c31f637736b7d14de9884d2da0fbfab7f09943f7918fb874b1d1f459093e71f030070bd152d3740391e381fa5349867279329ac19e5c9e6dc94ca441e1d6548a3e2b117a36b23d9de197ee3c427f972f9eae44c1b4f80f0f3eca97df7c8df74be122097a2d93ede92c20021799974322c95b1637c6c6792fb101f1408e0d25db464898aad7b9562be3fae10564aa2aaa50b81a229667473acb5377debde9c533c74022c553771ce1d7cfbcc8a5f26cdb763e92ac61325e2b474a30654b9faae78013b2c10be82c1ea85aa1e679288a12391ec8978df5a476e371929ac6afdef3210612e12300bb99c1648a637b0ff02bdffd2b2a6d3e7cafe8d4b1149573d393d1e281ec1466adfcc1890702f8f49df7f0f0e0d6ae74107682001ed9b2954fdf71cf463725149b4a401f191ae193070e77b408dacd2842f0c983877964cbc84637a56d368d8032bac1678f3cb0668ba1dd82a1aa7cf6c887c84448dab9116c0a012942707ccf010ee4c34f776dcfe3fcf4142f8d5d66bc5c5af3b5275f4ac6cb255e1abbccf9e9a9c0f5b9953858e8e51776ee43d9044b1d9bc2133d92cef0e4c1bb43dbcdd46bfc9befbdc0ff3a7f0ed7f7c918269fb9e7019ebcfbbe35194349e00fde7895677ff0b714ed3a9aa2f07777efe3f30f3d462ea4afeaa943f7f097ef5fe072b5024af7deb2bbbe075285e013fb0f455a3bfad26b2ff38d77df9e9f1d16ed3a5f7ce57b7cf7caa5d56e26002f8dbecf175f7989a25d07c0933edf78f71ccfbd167ec9a2d74af2c98377235c17223e50783de87a010da732fcf8b69d916c4fbf7f01b9e83650755dbe7b796d4247bf7be51255b7e154991be74b24a722d6f7133bf7309c4ae33b4ed78aa8eb05f4f1edbbe9b7a2390c932d9277a72324f50e53dfe24962945daf00fd89241fdfb50f24f84e77f6445d2da0fe44929fdcbe3bb2fdcfee39b064d7446f22c9e33b7675dab4401edfbe8bfee4ad62d7158d9fd97320f2397f7ad73e06922990b22b45d4d583e8bb0a7dec6db187aa1d8e1d3844d9b1f9b377dfa664db0ca7b37ceaeefbb9a37760155b7993033d7dfcdb0f7f8ce7de38c368b148da30f9993dfb39b6ffaec8e7dc9b2f70576f3fd72a6540e2bb2e8aa641c096a68da06b052468ec85573b701a5a9ac6a78e7c889fdb772765c7a13791083d1b0a83003eb66337f76f19e646ad4a4ad3e94fa63a727caa42e1b1ad3b387df96263d7869448d74574899fa86b0554302deeed5b3963c74a284230984aaf5c7015c999d6aa0af5dec1210a5682c95a63c7aa94125c072937fea9c71d084847130a5a731bb0a9a8917a0b436d6c1d5ebca3627fbeb719aa10b32393e540a197330b122e08049a27d184683cf425a494a46c7ef642d0890c22c7030da7920ce6f364549dbdb9023b92593c197e8037d48c075a3cdd3e3abc8d84d6b51de4ba92d074fede8e3d24e62604cd2f6ac1b0d89eca2006b6e2483f94735422c95a16d73d858b1b110f346d5710e53255ddc0d034c6caa548e11c55dfa3eababcb9289ce3e33bf6446dda6d49c6b4787b7a92851b950692490482b337c6b115b1d47fb01c52d29b4ce2d4cb943ac8f016391ea8e23828761d09dca855b9522e46125052d3d185724b7e1d550886d679dcd2ed0ca7338c57cab7447d4a2405d362b45ca2e67b084d6d5b4452425d4a4cc7c669c61fdd36f140fd8924056b73c639af153d5682fe4569f41af9c5e65cde12e97a0d65ac235d29a09c619150e3f1cf42129a1e30b35bd4db4889f4d657445d29a084a6b59d77e78382ae28b74e2a826e5582751751570ac852b5c0e4511f647445c59aeb955b89678e751451575e254b5563012d4257142c4d5b593c730724eb22a2ae1c689c9dbacebf78e1dbc836fc4a3bd3591404e74b3391ea3a98eb61c6b60353042fbd56b71ed0148583b902a3950a93763b790d6fb54f693abbb3392e146729b9f68252c133a9b353013b57966b635344426d7f761696ae14d0d56a85b16211bf5e5ff11b74a46f0005c1abd7a365362d0e8d70ad5ae1473353b71c5f493cd0885f2e0f8d706e7a922b0bb3cbb7bc56e2965ff386c554ff203fb83ece6433155fcb149eedf63c8b59631175ed7d42682a8a69aed937a75149f0b9db114f4bfb36c5135c6295c5b3e0f05addceba5640d01491b54622baadc4b372b500d2f7575d445d2d2000a1ae81883a11cfdcf31dda28bae48d76ca2cdfa0a5455b3daea0c529575b445d2f20586511752a9e60eb958faf917802ad830e2e38df6a8a6853080856494481b602b1e4f8fa88a7e5b34e5661ccb3d2f9a42f6115a289228773e8e824348d84aa91d2740a86851b219c236b18a475bdedc71348c3c2abd7a0990924ad190821c81be6f2862d04903674aa9e41de58a6fe16b686aa92d275328641de6d61bf4c8f97314c529a4ed630175dca453681fa16647493a4a693374d6a9eb6c2d8e8d6372590d5741455456fca605dc339fa53490633d9f978a02d56aaa378a05a9b1929003ccfc5add591d267673ad748f8bdece6bb561751b02793a3d74c9033cc16c5440b0d3402e97667b2988ada780c95587c8a25076e399ed274766772080425d70e1e482f73db2a181623e9348eefe3b4f8ec83ab1748d98c07b27dd289750c289b63a25cc12d161b8f011082cba5d948f98176db79869369ce84f4e348d7c5afd7717d1f45087e786322b8e00a631e535599a855b9303bddbe7df390a1a858aaca3b33d34bf31bb5d2ce82a359c34420f8e1e404b3cdcd882d8a061e1848a6707c9fd76e5c5b9a1f688531979492de640a512b52aa864b0db390c8f1400e0e15d74153148a8ecd64bd16291ea8d74e90d18d484fa891488aae832259f24c0ca0ad0173c97128daf5f6ed171c32149592e330bbd8becda9ba044aaecdac5d9f7724b63ec7d2935a9a46c97398aad796e6076a31de9baf5b82a66918b687c36d160fd42e4253d12c2b78ef78bbb32d31ffcfcaf6adae8958a10cadfd3c6dddb6c20c986145f1b42c1281ae5cca0883a2698d607e216e4e4dd7cb49b8f83615d649185cb88d8a5bb5a7bd99e2c28faa5336750f3487d0b49b53fc4de5615e61b6b546e2594d6e0b01c1f27ea28df230afe5da563b45d75a3c701b09089a22328d5b3ea9f6c4d3ea842d6b5af47b8068d74b3c2ddf68433c02e830f0f3b61210dc14911041fe9b4ec6222dde08d4c37a89a7f5d07ca56a97ad2b04b79d80a02122b1a8276ae995696b2c12f04670c7b38eb7ad0ec73cab743fbb2d05044d11197322ea8601f34af584bd6dad5c78adc503b7b18060b18816bfb989c5d3899f679547d2b7b580604e44faad1fdce20f71994eaa1df174d4c3b5baa01d08208c783a95d36d2f205824a250dfc036c4d35200ed88a7e5d838c4396f55ff7af53c737c200404ad06d6cb5a04fe7a6b894e6f5b6dde465bfaa836e6b6b590c8f14049bd11c792d50d7a4c8b7a321d291e682091a4d732198e984ca1cf4ca008d1b6bdf47ca46d375cf9027acc04be8492632f28d5de375a57540a86c54032894436addabf6d6574b3699f6aeef90aaca865fd03891405d3622895a21ef8b8a7e02e4e0048494f22855baf6f4c3c50de4832984a91517576a4b31474aba378a099babd72e1007665b22842e0b519092001e97bb8b60d1246526932bad14870b5f8f35e6eaa2c40130a23a9340a82826905f74801e2993b94d2f4f9789e9217fccca5604d358ef6981623e90c65c7c5091250ab651d6ee6079aa8d4d7371e682e9c63b45ca132334d5637a9fb1e978ab33811c239f6e60a6c4da6f9ced5cba16d018aae832a046726ae86b293be8f6cf63a13b50aefcc4c11f6b665a82a42087e343dc95825e079672bf42879d3c46ec6f32c092711cb34a2797c2895a2e4b87cefda955b03f296b56d2025f42793689559a6aad1c3393a588d77707d1f57fad8be4fd57323c503d53d0fdbf7433fef6a0edbf7508488642f150547fad43d8fdac280ac36c73c3e12c7f7a807b5bf8da97acdd316d4bf5000ed3909ebbe87e37bd45cb74dfb9b480935dfc39412881e50f681194407211405d5306e0daa0f39605ef5789e2ef130b7cb075a40008aa2a2cc4ff183cbb48ee7d9c41ee6e53cf421f8c00b0840282a420fce7cb1ec543dc0afb3c281650f47f6308b566f84a9271ab1809a0845412c4a9fb2aef13c1d85a16e8c78a01301f9aa58a66fdf942c14d1e658180de9590fea61a50019fd8164e10574ee9c04705dd7939268ce9b2e46284ae35914ed0e703b144f6b4fcdf2d52e77cee0b2c1857dcf733cc76d4c41cf9f0f1d291d5e40fdfd12a0f4fd5727f0bc1b00627196f0cdce7c4fb4e0585bdff42e9d6d05979548895bad4e97df387b0d804c661d0474f2a4646444545e7db52e5df7ddb9a6843e4fb7b3704cd4aae7118b0f04b0ac78961f85afa1784008e9bb2ede6cf972fddd77a71919119c3e1dda9117690c3478678f02605f19fd96f4fdd5980d7627413d11b4ffffedce9e677e4f8f33334be9ead8ff0664ffde5ca4e8e8280292e3e72b2ee08ffff6ef7cdd9d9e7e4f4aa9226474776637a388667ab8e6df016b5bedf983e81ef128c2c573557be2fae8db5f3bf955c09fb858768990ae23da2cec9d77fcccfe61b53e315b9b7ce9d5dfa84d4e818f8690ee7a674a5f1714e55611cd13223f4f2b3a08c968e90a5c493c08cdb93cc6f46b6f3e63dfb851caec1f56b97831fcf3c989be16268be7466bf4f458efff973ffa3ffdb9ecafdbdb863e277a0a1abaee351bdb9eab534a64633c17b12572e14b787364a30d2bd90b0514d1c83548231644cae6b3bb16da0b82bfc7422c392e69d8cba67dcb1da301b68d13dcfcec6ed61f585622844408705ccdbe324af5ed73cf5c7bfe4fbf99e8e9a1786eb4d6a2d52b12454012f00197c9c9aa99cb99affcd6b37fbcfb1f1dbf9e3e74f0b35aa130ace7b2a8567bc15b854482bc652d790e44bbe4ad463c50747b0b5bfaf4db6dda4bbf99b0b2b11a9fb52c7a92496c7c5a0fa4838fe70c93ac65d19b4ca1053dda4accff13484f2245d6b2e84f26a9cd877304f749bee3e04c4d531bbf3656fde15b5fb8f6dffec737cd5cceab4e4e5668aca6fa441051d4e1afa0213e13481b994cde2e16f5444f4f7acff19fff07c9a18147b5747a584b585945377489d7b2610533414637b9546a915e6505b62452082118ab94562e1cc0b6749e8a6b73a35669db467a12e97b280846b259a6aa558a4eab789ed6e3234b53194ca6b9562d537503ee2001038c85674b1b06bd668a2ba519dc806b2fa422a4ef394ea532ebce16472b57c6bef3ee9ffef99f5427278b4626e3d8c5e2345002ea3444b46e0282c67f4f071240cae84d67ed1b2555d7750d50d49e9ebcb96b7b9fa26a1a7e6b7fa3aa18e82ad49c683e4943693c6adb5ea68ee5b07403df8b68ef35ec1ddfc35b9c9fa71d54154b57a9395ee36461cd69d8975bdaabf8527af50b9726bcc9c919c0731cc7357ad39e7da3340b94812ae0d0e88142d389800437456401494c33692475cb2edb0ab68daeebf15a5b17e0388e8f6160a40cdfae3835eaf50a50016adc144fa43150a71e9c85223268dcd24c40c7340da454308c65fd0b46f335ea9ac866b63716fcde89fdb2b6b6ed218447bdeed0104bbdf963d3a17860755c80732252698c8b341a82529bc75b795062d616b9e0d5a7718f736988c66dfedd917860f52eeadc7994453f0ba7f2b180d69785029a13d1c29f856522b3da1775b15862d17407b2c56bc7acf5058e05d41ddc86cb033131313131313131313131313131313131ebc9ff07cfa5702d3fd0aa390000000049454e44ae426082
'))
where test_id1=1;

select * from test_table;


INSERT INTO test_table (test_id1, test_id2, test_id3, test_money1, test_money2, test_name, head_base64, test_age1, test_age2, test_age3, price1, price2, birth_date, test_my_remark) VALUES (2, 2, 3, 9.99, 1999.99, '李四', '1225dfdfdfdfd', 20, 21, 23, 9.99, 1999.99, '2019-12-12', '我会唱跳rap,篮球');
INSERT INTO test_table (test_id1, test_id2, test_id3, test_money1, test_money2, test_name, head_base64, test_age1, test_age2, test_age3, price1, price2, birth_date, test_my_remark) VALUES (99, null, null, null, null, '李四', null, 100, null, null, null, null, null, '快100岁了');
INSERT INTO test_table (test_id1, test_id2, test_id3, test_money1, test_money2, test_name, head_base64, test_age1, test_age2, test_age3, price1, price2, birth_date, test_my_remark) VALUES (99, null, null, null, null, '李四', null, 99, null, null, null, null, null, '快100岁了');
INSERT INTO test_table (test_id1, test_id2, test_id3, test_money1, test_money2, test_name, head_base64, test_age1, test_age2, test_age3, price1, price2, birth_date, test_my_remark) VALUES (99, null, null, null, null, '李四', null, 99, null, null, null, null, null, '快100岁了');
INSERT INTO test_table (test_id1, test_id2, test_id3, test_money1, test_money2, test_name, head_base64, test_age1, test_age2, test_age3, price1, price2, birth_date, test_my_remark) VALUES (99, null, null, null, null, '李四', null, 99, null, null, null, null, null, '快100岁了');
INSERT INTO test_table (test_id1, test_id2, test_id3, test_money1, test_money2, test_name, head_base64, test_age1, test_age2, test_age3, price1, price2, birth_date, test_my_remark) VALUES (99, null, null, null, null, '李四', null, 99, null, null, null, null, null, '快100岁了');
INSERT INTO test_table (test_id1, test_id2, test_id3, test_money1, test_money2, test_name, head_base64, test_age1, test_age2, test_age3, price1, price2, birth_date, test_my_remark) VALUES (99, null, null, null, null, '', null, 99, null, null, null, null, null, '快100岁了');

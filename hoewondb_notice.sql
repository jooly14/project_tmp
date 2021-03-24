-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.4.17-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- hoewondb 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `hoewondb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hoewondb`;

-- 테이블 hoewondb.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(100) DEFAULT NULL,
  `regdate` date NOT NULL DEFAULT sysdate(),
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 테이블 데이터 hoewondb.notice:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` (`idx`, `title`, `content`, `regdate`) VALUES
	(1, '도서관에서 겨울나기 \'집콕 놀이 체험 행사\' 안내', NULL, '2021-01-13'),
	(2, '1월 \'아이 좋아\'도서관 운영 안내(1.9)/비대면', NULL, '2021-01-05'),
	(3, '신정(1/1) 휴관 안내', NULL, '2020-12-29'),
	(4, '마산회원도서관 성탄절(12/25) 휴관 안내', NULL, '2020-12-22'),
	(5, '12월 문화가 있는 날 안내', NULL, '2020-12-21');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

DROP DATABASE IF EXISTS fixerpro;

CREATE DATABASE fixerpro;

USE fixerpro; 

DROP TABLE IF EXISTS engine_oil;

CREATE TABLE engine_oil(
	id bigserial NOT NULL PRIMARY KEY,
    title varchar(100) NOT NULL,
    thumbnail varchar(100) NOT NULL,
    liters varchar(100) NOT NULL,
    price varchar(100) NOT NULL
);

INSERT INTO engine_oil(title, thumbnail, liters, price) VALUES
('CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L', '/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg', 5, '84.90'),
('CASTROL EDGE 5W-30 LL 5L', '/thumbnails/5l/castrol-edge-5w-30-ll-5l.jpg', 5, '94.90'),
('MOTUL 6100 SYNERGIE+ 10W-40 5L', '/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg', 5, '78.56'),
('CASTROL MAGNATEC DIESEL 10W-40 B4 5L', '/thumbnails/5l/castrol-magnatec-diesel-10w40-5l.jpg', 5, '75.90'),
('CASTROL MAGNATEC DIESEL 5W-40 DPF 5L', '/thumbnails/5l/castrol-magnatec-diesel-5w-40-dpf-5l.jpg', 5, '79.90'),
('CASTROL EDGE 5W-30 M 5L', '/thumbnails/5l/castrol-edge-5w-30-5l.jpg', 5, '94.90'),
('MOTUL 8100 X-CESS 5W-40 5L', '/thumbnails/5l/motul-8100-x-cess-5w-40-5l.jpg', 5, '89.82'),
('CASTROL MAGNATEC 10W-40 A3/B4 5L', '/thumbnails/5l/castrol-magnatec-10w-40-5l.jpg', 5, '75.90'),
('CASTROL MAGNATEC 5W-40 C3 5L', '/thumbnails/5l/castrol-magnatec-5w40-c3-5l.jpg', 5, '79.90'),
('MOBIL SUPER 3000 X1 5W-40 5L', '/thumbnails/5l/mobil-super-3000-x1-5w-40-5l.jpg', 5, '85.90'),
('MOBIL SUPER 2000 X1 10W-40 5L', '/thumbnails/5l/mobil-super-2000-x1-10w-40-5l.jpg', 5, '55.90'),
('CASTROL EDGE FST TITANIUM 5W-40 5L', '/thumbnails/5l/castrol-edge-fst-titanium-5w-40-5l.jpg', 5, '95.90'),
('MOBIL ESP FORMULA 5W-30 5L', '/thumbnails/5l/mobil-esp-formula-5w-30-5l.jpg', 5, '119.90'),
('MOTUL 8100 X-CLEAN 5W-40 5L', '/thumbnails/5l/motul-8100-x-clean-5w-40-5l.jpg', 5, '105.29'),
('MOTUL 8100 X-CLEAN EFE 5W-30 5L', '/thumbnails/5l/motul-8100-x-clean-fe-5w30-5l.jpg', 5, '102.77'),
('MOTUL SPECIFIC CNG/LPG 5W-40 5L', '/thumbnails/5l/motul-specific-cng-lpg-5w-40-5l.jpg', 5, '107.81'),
('CASTROL MAGNATEC STOP-START 5W-30 A5 5L', '/thumbnails/5l/castrol-magnatec-5w-30-a5-5l.jpg', 5, '89.90'),
('MOTUL SPECIFIC VW 505.01/502.00 5W-40 5L', '/thumbnails/5l/motul-specific-vw-50501-5w-40-5l.jpg', 5, '103.77'),
('MOTUL 6100 SYNERGIE+ 10W-40 5+1L', '/thumbnails/5l/motul-6100-synergie-10w-40-51l.jpg', 5, '97.70'),
('MOTUL 8100 ECO-CLEAN 5W-30 5L', '/thumbnails/5l/motul-8100-eco-clean-5w-30-5l.jpg', 5, '112.55'),
('CASTROL EDGE 0W-30 5L', '/thumbnails/5l/castrol-edge-0w-30-5l.jpg', 5, '115.90'),
('MOBIL SUPER 3000 XE 5W-30 5L', '/thumbnails/5l/mobil-super-3000-xe-5w-30-5l.jpg', 5, '89.90'),
('MOBIL 1 FS 0W40 5L', '/thumbnails/5l/mobil-1-new-life-0w-40-5l.jpg', 5, '124.90'),
('MOTUL 8100 X-CLEAN+ 5W-30 5L', '/thumbnails/5l/motul-8100-x-clean-plus-5w30-5l.jpg', 5, '118.31'),
('MOTUL 4100 POWER 15W-50 5L', '/thumbnails/5l/motul-4100-power-15w-50-5l.jpg', 5, '88.34'),
('MOTUL SPECIFIC VW 504.00/507.00 5W-30 5L', '/thumbnails/5l/motul-specific-vw-5040050700-5w-30-5l.jpg', 5, '118.49'),
('MOTUL SUBARU C2 5W-30 5L', '/thumbnails/5l/motul-subaru-c2-5w30-5l.jpg', 5, '134.10'),
('MOTUL 8100 ECO-CLEAN 0W-30 5L', '/thumbnails/5l/motul-8100-eco-clean-0w30-5l.jpg', 5, '136.57'),
('CASTROL EDGE 10W-60 5L', '/thumbnails/5l/castrol-edge-10w-60-5l.jpg', 5, '126.90'),
('MOTUL SPECIFIC 913D 5W-30 5L', '/thumbnails/5l/motul-specific-913d-5w-30-5l.jpg', 5, '97.07'),
('MOTUL 8100 X-CESS GEN2 5W-40 5L', '/thumbnails/5l/motul-8100-x-cess-gen2-5w-40-5l.jpg', 5, '92.58'),
('CASTROL EDGE TURBO DIESEL 5W-40 1L', '/thumbnails/1l/castrol-edge-turbodiesel-5w40-1l.jpg', 1, '21.90'),
('CASTROL EDGE 5W-30 M 1L', '/thumbnails/1l/castrol-edge-5w30-1l.jpg', 1, '23.90'),
('CASTROL EDGE 5W-30 LL 1L', '/thumbnails/1l/castrol-edge-5w-30-ll-1l.jpg', 1, '23.90'),
('CASTROL MAGNATEC DIESEL 5W-40 DPF 1L', '/thumbnails/1l/castrol-mag-diesel-5w40-1ldpf.jpg', 1, '18.90'),
('MOTUL 8100 X-CESS 5W-40 1L', '/thumbnails/1l/motul-8100-x-cess-5w-40-1l.jpg', 1, '22.07'),
('CASTROL MAGNATEC DIESEL 10W-40 B4 1L', '/thumbnails/1l/castrol-magnatec-diesel-10w40-1l.jpg', 1, '17.90'),
('MOBIL SUPER 3000 X1 5W-40 1L', '/thumbnails/1l/mobil-super-3000-x1-5w-40-1l.jpg', 1, '17.90'),
('CASTROL MAGNATEC 5W-40 C3 1L', '/thumbnails/1l/castrol-magnatec-5w-40-c3-1l.jpg', 1, '18.90'),
('CASTROL EDGE FST TITANIUM 5W-40 1L', '/thumbnails/1l/castrol-edge-fst-titanium-5w-40-1l.jpg', 1, '23.90'),
('CASTROL MAGNATEC 10W-40 A3/B4 1L', '/thumbnails/1l/castrol-magn10w40-1l.jpg', 1, '17.90'),
('MOTUL 6100 SYNERGIE+ 10W-40 1L', '/thumbnails/1l/motul-6100-synergie-10w-40-1l.jpg', 1, '19.14'),
('MOTUL MOTYLGEAR 75W-90 1L', '/thumbnails/1l/motul-motylgear-75w-90-1l.jpg', 1, '23.94'),
('MOBIL ESP FORMULA 5W-30 1L', '/thumbnails/1l/mobil-esp-formula-5w-30-1l.jpg', 1, '25.90'),
('CASTROL EDGE 0W-30 1L', '/thumbnails/1l/castrol-edge-0w-30-1l.jpg', 1, '25.90'),
('MOBIL SUPER 2000 X1 10W-40 1L', '/thumbnails/1l/mobil-super-2000-x1-10w-40-1l.jpg', 1, '12.90'),
('CASTROL 2T 1L Moto', '/thumbnails/1l/castrol-2t-1l.jpg', 1, '15.90'),
('MOTUL 8100 X-CLEAN 5W-40 1L', '/thumbnails/1l/motul-8100-x-clean-5w-40-1l.jpg', 1, '25.74'),
('CASTROL TRANSMAX LS 75W-140 LL 1L', '/thumbnails/1l/castrol-syntrax-ls-75w-140-1l.jpg', 1, '39.90'),
('CASTROL MAGNATEC STOP-START 5W-30 A5 1L', '/thumbnails/1l/castrol-magnatec-5w-30-a5-1l.jpg', 1, '21.90'),
('MOTUL 8100 ECO-CLEAN 5W-30 1L', '/thumbnails/1l/motul-8100-eco-clean-5w-30-1l.jpg', 1, '27.58'),
('MOBIL 1 FS 0W40 1L', '/thumbnails/1l/mobil-1-new-life-0w-40-1l.jpg', 1, '29.90'),
('MOBIL SUPER 3000 XE 5W-30 1L', '/thumbnails/1l/mobil-super-3000-xe-5w-30-1l.jpg', 1, '19.90'),
('MOTUL 8100 X-CLEAN+ 5W-30 1L', '/thumbnails/1l/motul-8100-x-clean-plus-5w-30-1l.jpg', 1, '27.92'),
('MOTUL 8100 X-CLEAN EFE 5W-30 1L', '/thumbnails/1l/motul-8100-x-clean-fe-5w-30-1l.jpg', 1, '23.87'),
('CASTROL EDGE PROFESSIONAL LONGLIFE III 5W-30 1L', '/thumbnails/1l/castrol-edge-professional-longlife-iii-5w30-1l.jpg', 1, '24.90'),
('MOTUL SCOOTER EXPERT T4 10W-40 1L Moto', '/thumbnails/1l/motul-scooter-exp-t4-10w-40-1l.jpg', 1, '22.32'),
('CASTROL EDGE 10W-60 1L', '/thumbnails/1l/castrol-edge-10w-60-1l.jpg', 1, '29.90'),
('MOTUL MULTI ATF 1L', '/thumbnails/1l/motul-multi-atf-1l.jpg', 1, '27.76'),
('MOTUL GEARBOX 80W-90 1L', '/thumbnails/1l/motul-gearbox-80w-90-1l.jpg', 1, '28.08'),
('MOTUL 8100 ECO-CLEAN 0W-30 1L', '/thumbnails/1l/motul-8100-eco-clean-0w-30-1l.jpg', 1, '30.08'),
('MOTUL SPECIFIC CNG/LPG 5W-40 1L', '/thumbnails/1l/motul-specific-cng-lpg-5w-40-1l.jpg', 1, '24.77'),
('CASTROL POWER 1 10W-40 4T 1L Moto', '/thumbnails/1l/castrol-power-1-4t-10w-40-1l.jpg', 1, '23.90');
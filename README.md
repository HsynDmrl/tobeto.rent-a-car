# Tobeto Rent-a-Car Projesi

Bu proje, Tobeto kursu kapsamında verilen ödevin bir parçasıdır. Proje, bir araç kiralama sistemini temsil etmektedir.

## ER Diagramı

Projenin güncellenmiş veritabanı tasarımını gösteren ER (Entity-Relationship) diagramı aşağıda yer almaktadır:

![ER Diagram](https://github.com/HsynDmrl/tobeto.rent-a-car/blob/main/20.11.2023ER-diagram.png)

## SQL Dosyası

Veritabanı işlemleri için kullanılan SQL dosyasına [buradan](./18.11.2023SQL.pgerd) erişebilirsiniz.

Projeyle ilgili daha fazla detay ve sorgu için SQL dosyasını inceleyebilirsiniz.
Diagramda görüldüğü gibi, veritabanındaki farklı entity'ler arasında ilişkiler kurulmuş ve verilerin nasıl organize edildiği gösterilmiştir.

## Veri İlişkileri

Projede kullanılan ilişkileri daha iyi anlamak için [db_iliskiler.pdf](https://github.com/HsynDmrl/tobeto.rent-a-car/blob/main/db_iliskiler.pdf) adlı bir eğitim dokümanı eklenmiştir. Bu doküman, one-to-many ve one-to-one ilişkilerini açıklamaktadır. İlgili bağlantıya tıklayarak PDF dosyasını görüntüleyebilir veya indirebilirsiniz.

## Güncelleme - 20 Kasım 2023

### Yapılan Değişiklikler

- Yeni Controller'lar Eklendi: Projeye yeni özellikler eklemek amacıyla `controllers` dizinine yeni controller'lar eklendi. Bu controller'lar, projenin farklı modüllerini yönetmek için kullanılmaktadır.

- Yeni Entity ve Repository'ler Eklendi: Veritabanı işlemlerini daha etkili yönetmek için yeni entity ve repository sınıfları oluşturuldu. Bu sayede veritabanı operasyonları daha modüler hale getirildi.

- Güncellenmiş ER Diagramı: Veritabanı ilişkilerini yansıtan ER diagramı güncellendi. Yeni entity'ler ve ilişkiler diagramda yer almaktadır.

- Güncellenmiş SQL Dosyası: Veritabanı tablolarını oluşturmak için kullanılan SQL dosyası güncellendi. Yeni eklenen tablolar ve ilişkiler bu dosyada yer almaktadır.

- Eğitim Dokümanı Eklendi: Proje içinde kullanılan ilişkileri daha iyi anlamak için `db_iliskiler.pdf` adlı bir eğitim dokümanı eklendi. Bu doküman, one-to-many ve one-to-one ilişkilerini açıklamaktadır.

## Kurulum

Projeyi yerel makinenizde çalıştırmak için aşağıdaki adımları takip edebilirsiniz:

1. Repoyu bilgisayarınıza klonlayın: 
   ```bash
   git clone https://github.com/HsynDmrl/tobeto.rent-a-car.git

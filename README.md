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

## Güncelleme - 23 Kasım 2023

### Yapılan Değişiklikler

- **Controller'lar Güncellendi:** Tüm controller'lar, projeye yeni özellikler eklemek amacıyla güncellendi. Yapılan değişiklikler, projenin daha esnek ve genişletilebilir olmasını sağlamaktadır.

- **Yeni DTO'lar Eklendi:** Projeye, talep (requests) ve cevap (responses) verilerini işlemek üzere iki ayrı DTO (Data Transfer Object) eklendi. Bu DTO'lar, veri transferini düzenlemek ve kontrol etmek için kullanılmaktadır.

- **DTO'lar Entitilerle Bağlandı:** Yeni eklenen DTO'lar, controller'lar aracılığıyla entitilere bağlandı. Bu sayede veri işlemleri daha spesifik ve verimli bir şekilde gerçekleştirilebilmektedir.

- **Entities Güncellendi:** Var olan entity sınıfları, projenin yeni gereksinimlerine uyacak şekilde güncellendi. Bu değişiklikler, veritabanı operasyonlarını daha etkili bir hale getirmektedir.

## Güncelleme - 20 Kasım 2023

### Yapılan Değişiklikler

- Yeni Controller'lar Eklendi: Projeye yeni özellikler eklemek amacıyla `controllers` dizinine yeni controller'lar eklendi. Bu controller'lar, projenin farklı modüllerini yönetmek için kullanılmaktadır.

- Yeni Entity ve Repository'ler Eklendi: Veritabanı işlemlerini daha etkili yönetmek için yeni `entities` ve `repositories` sınıfları oluşturuldu. Bu sayede veritabanı operasyonları daha modüler hale getirildi.

- Güncellenmiş ER Diagramı: Veritabanı ilişkilerini yansıtan ER diagramı güncellendi. Yeni entity'ler ve ilişkiler diagramda yer almaktadır.

- Güncellenmiş SQL Dosyası: Veritabanı tablolarını oluşturmak için kullanılan SQL dosyası güncellendi. Yeni eklenen tablolar ve ilişkiler bu dosyada yer almaktadır.

- Eğitim Dokümanı Eklendi: Proje içinde kullanılan ilişkileri daha iyi anlamak için `db_iliskiler.pdf` adlı bir eğitim dokümanı eklendi. Bu doküman, one-to-many ve one-to-one ilişkilerini açıklamaktadır.

## Kurulum

Projeyi yerel makinenizde çalıştırmak için aşağıdaki adımları takip edebilirsiniz:

1. Repoyu bilgisayarınıza klonlayın: 
   ```bash
   git clone https://github.com/HsynDmrl/tobeto.rent-a-car.git

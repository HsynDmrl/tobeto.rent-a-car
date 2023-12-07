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

## Güncelleme - 08 Aralık 2023

### Yapılan Değişiklikler

- **Add Request'lerine Validation ve Business Rule Eklendi:** Projede bulunan tüm "add" işlemlerine (örneğin, araç ekleme, müşteri ekleme vb.) validation kontrolleri eklenmiştir. Ayrıca, en az bir business rule örneği de implemente edilmiştir.

#### Örnek Validation Kontrolleri

1. **Araç Ekleme İşlemi İçin:**
   
    **VehiclesController:**
    ```java
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddOrderRequest addOrderDto){
        this.orderService.add(addOrderDto);
    }
    ```

    **AddOrderRequest:**
    ```java
    @NotNull(message = "Teslim tarihi boş olamaz.")
    private LocalDate pickUpDate;

    @NotNull(message = "Alım tarihi boş olamaz.")
    private LocalDate dropDate;
    ```

2. **Business Rule Kontrolü Örneği:**
   ```java
   if (orderRepository.existsByPickUpDateAfterAndDropDateBefore(
           addOrderDto.getDropDate(),
           addOrderDto.getPickUpDate())) {
       throw new RuntimeException("Alım tarihi, teslim tarihinden önce olmalıdır.");
   }

- **Global Exception Handling Ekledi:** Projeye global exception handling mekanizması eklenmiştir. Bu sayede projede ortaya çıkan `RuntimeException` türündeki hatalar kontrol edilebilmekte ve düzenli bir şekilde yönetilmektedir.

#### Global Exception Handling Örneği

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeException(RuntimeException exception)
    {
        return exception.getMessage();
    }
}

## Güncelleme - 06 Aralık 2023

### Yapılan Değişiklikler

- **Veritabanına Yeni Özellikler Eklendi:** Projeye, veritabanında en az 5 entity için her birine en az 2 derived method ve en az 2 JPQL query örneği eklenmiştir. Bu örnekler aşağıdaki entity'ler için uygulanmıştır:
  - Customer
  - Employee
  - Insurance
  - Maintenance
  - Payment

## Güncelleme - 26 Kasım 2023

### Yapılan Değişiklikler

- **Services Katmanı Eklendi:** Projeye yeni bir "services" katmanı eklendi. Bu katman, `abstract` ve `concrete` sınıfları içererek, iş mantığını daha iyi düzenlemek ve daha modüler bir yapı oluşturmak amacıyla tasarlandı.

- **Requests ve Responses Service Katmanına Taşındı:** Daha önceki `talep (requests)` ve `cevap (responses)` verileri, bu güncelleme ile "services" katmanına taşındı. Bu sayede iş mantığı, bu katman aracılığıyla daha düzenli bir şekilde yönetilmektedir.

- **Koşullar Eklendi:** Projedeki manager kısımlarına yani güncellenen,yeni eklenen ve silinecek olan bilgileri kontrol etmek için, koşullar eklendi. Örneğin:
  ```java
  if (updateVehicleDto.getBrand().length() < 2)
      throw new RuntimeException("Güncellenen Marka bilgisi 2 karakterden kısa olamaz.");

## Güncelleme - 23 Kasım 2023

### Yapılan Değişiklikler

- **Controller'lar Güncellendi:** Tüm controller'lar, projeye yeni özellikler eklemek amacıyla güncellendi. Yapılan değişiklikler, projenin daha esnek ve genişletilebilir olmasını sağlamaktadır.

- **Yeni DTO'lar Eklendi:** Projeye, `talep (requests)` ve `cevap (responses)` verilerini işlemek üzere iki ayrı DTO (Data Transfer Object) eklendi. Bu DTO'lar, veri transferini düzenlemek ve kontrol etmek için kullanılmaktadır.

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

# java.time.* 使用记录
>* LocalDate->java.util.Date<br/>
``
LocalDate localDate = LocalDate.now();
Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
``

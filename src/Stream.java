//
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//    public class SoldProductsAggregator {
//
//        private final EURExchangeService exchangeService;
//
//        SoldProductsAggregator(EURExchangeService EURExchangeService) {
//            this.exchangeService = EURExchangeService;
//        }
//
//        SoldProductsAggregate aggregate(Stream<SoldProduct> products) {
//            Stream<SoldProduct> stream =products== null ? Stream.empty(): products;
//            List<SimpleSoldProduct> collect = stream.map(prod-> new SimpleSoldProduct(prod.getName(),getEuro(prod).orElseGet(()->null))).filter(simplPord-> Objects.nonNull(simplPord.getPrice())).collect(Collectors.toList());
//            return new SoldProductsAggregate(collect,collect.stream().map(SimpleSoldProduct::getPrice).reduce(BigDecimal.ZERO, this::addDecimal));
//        }
//
//        Optional<BigDecimal> getEuro(SoldProduct prod){
//            Optional<BigDecimal> rate = exchangeService.rate(prod.getCurrency());
//            rate = (rate ==null ? Optional.empty(): rate);
//            return rate.map( rate1-> rate1.compareTo(BigDecimal.ZERO) > 0 ?prod.getPrice().multiply(rate1):null  );
//        }
//
//
//        BigDecimal addDecimal(BigDecimal x, BigDecimal y){
//            return x.add(y);
//        }
//
//
//        @Value
//        class SoldProductsAggregate {
//            List<SimpleSoldProduct> products;
//            BigDecimal total;
//
//            public SoldProductsAggregate(List<SimpleSoldProduct> products, BigDecimal total) {
//                this.products = products;
//                this.total = total;
//            }
//        }
//
//        @Value
//        class SoldProduct {
//            String name;
//            BigDecimal price;
//            String currency;
//
//            public String getName() {
//                return name;
//            }
//
//            public BigDecimal getPrice() {
//                return price;
//            }
//
//            public String getCurrency() {
//                return currency;
//            }
//        }
//        @Value
//        class SimpleSoldProduct {
//            String name;
//            BigDecimal price;
//
//            public SimpleSoldProduct(String name, BigDecimal price) {
//                this.name = name;
//                this.price = price;
//            }
//        }
//
//
//        public interface EURExchangeService{
//            Optional<BigDecimal> rate(String currency);
//        }
//
//    }
//

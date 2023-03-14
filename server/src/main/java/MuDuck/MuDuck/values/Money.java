package MuDuck.MuDuck.values;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <h3>Spring Data JPA 실습 Solution 코드 포함</h3>
 * Money 클래스는 돈을 의미하는 Value Object입니다.
 * <p>&nbsp;</p>
 * <h4>Money 클래스에 대한 추가 설명</h4>
 * <ul>
 *     <li>
 *         <a href="https://docs.oracle.com/javaee/7/api/javax/persistence/Embeddable.html" target="_blank">
 *              {@literal @}Embeddable
 *         </a>
 *         <ul>
 *             <li>
 *                 <a href="https://docs.oracle.com/javaee/7/api/javax/persistence/Embeddable.html" target="_blank">
 *                      {@literal @}Embeddable
 *                 </a>
 *                 애너테이션이 추가된 클래스의 필드는
 *                 <a href="https://docs.oracle.com/javaee/7/api/javax/persistence/Embedded.html" target="_blank">
 *                     {@literal @}Embedded
 *                 </a>
 *                  애너테이션이 추가된 Entity 클래스 필드에 포함될 수 있습니다.
 *             </li>
 *         </ul>
 *     </li>
 *     <li>
 *         <a href="https://projectlombok.org/api/lombok/Getter" target="_blank">
 *             {@literal @}Getter
 *         </a>
 *         <ul>
 *             <li>
 *                 기본적으로 클래스 레벨에 lombok의
 *                 <a href="https://projectlombok.org/api/lombok/Getter" target="_blank">
 *                    {@literal @}Getter
 *                 </a>를 추가하면 클래스의 모든 필드에 getter 메서드가 생깁니다.
 *             </li>
 *         </ul>
 *     </li>
 *     <li>
 *         <a href="https://projectlombok.org/api/lombok/Builder" target="_blank">
 *             {@literal @}Builder
 *         </a>
 *         <ul>
 *             <li>
 *                 Mapstruct의 매핑 기능을 위해
 *                 <a href="https://projectlombok.org/api/lombok/Builder" target="_blank">
 *                    {@literal @}Builder
 *                 </a>애너테이션을 사용합니다.
 *                 setter는 불변 객체를 만들 수 없으므로 제외했습니다.
 *             </li>
 *         </ul>
 *     </li>
 *     <li>
 *         <a href="https://projectlombok.org/features/constructor" target="_blank">
 *             {@literal @}AllArgsConstructor
 *         </a>
 *         <ul>
 *             <li>
 *                 클래스에 있는 모든 필드에 해당하는 값을 파라미터로 가지는 생성자를 추가합니다.
 *                 Mapstruct는
 *                 <a href="https://projectlombok.org/features/constructor" target="_blank">
 *                      {@literal @}NoArgsConstructor
 *                 </a>
 *                 와 함께 사용하면
 *                 <a href="https://projectlombok.org/features/constructor" target="_blank">
 *                      {@literal @}AllArgsConstructor
 *                 </a>
 *                 가 정상 동작하지 않는다것을 기억하세요.
 *             </li>
 *         </ul>
 *     </li>
 *     <li>
 *         <a href="https://projectlombok.org/features/constructor" target="_blank">
 *             {@literal @}NoArgsConstructor
 *         </a>
 *         <ul>
 *             <li>
 *                 파라미터가 없는 디폴트 생성자를 추가합니다.
 *                 Spring Data JPA의 경우 Entity에 디폴트 생성자가 존재하지 않으면 데이터 조회 시, 에러가 발생합니다.
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * @author 황정식
 * @version 1.0.0
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/persistence/Embeddable.html"
 * target="_blank">@Embeddable</a>
 * @see <a href="https://projectlombok.org/features/constructor"
 * target="_blank">@AllArgsConstructor</a>
 * @see <a href="https://projectlombok.org/features/constructor"
 * target="_blank">@NoArgsConstructor</a>
 * @see <a href="https://projectlombok.org/api/lombok/Setter" target="_blank">@Setter</a>
 * @see <a href="https://projectlombok.org/api/lombok/Getter" target="_blank">@Getter</a>
 * @see <a href="https://projectlombok.org/api/lombok/Builder" target="_blank">@Builder</a>
 */
@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Money {

    /**
     * 수정될 수도 있고 수정되지 않을 수도 있는 값으로 사용될 수 있기 때문에 null을 통해 판단할 수 있어야 합니다. 따라서 int가 아닌 Integer 타입이어야
     * 합니다.
     * <ul>
     *     <li>
     *         ex) <a href="https://github.com/codestates-seb/be-solution-jpa/blob/main/src/main/java/com/codestates/coffee/service/CoffeeService.java" target="_blank">
     *                  CoffeeService
     *             </a>의 updateCoffee()의 Optional 부분
     *         <ul>
     *              <li>
     *                    <code>
     *                           Optional.ofNullable(coffee.getPrice().getValue())
     *                               .ifPresent(price -&gt; findCoffee.setPrice(new Money(price)));
     *                    </code>
     *              </li>
     *        </ul>
     *     </li>
     * </ul>
     */
    private Integer value;
}

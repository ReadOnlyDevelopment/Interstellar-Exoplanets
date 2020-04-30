package net.rom.api.stellar.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * @author ROMVoid
 * 
 * @apiNote Not In Use
 *
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD })
public @interface SolarSystemType {

}

package net.ent.etrs.commons.utils.cdiUtils.annotations;


import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * Annotation à ajouter devant la méthode à éxécuter dans le context Weld.
 *
 * @author Christophe Louër
 */
@Qualifier

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodWeldExecute {

}

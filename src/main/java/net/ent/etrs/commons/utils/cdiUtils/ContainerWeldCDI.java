package net.ent.etrs.commons.utils.cdiUtils;

import net.ent.etrs.commons.utils.cdiUtils.annotations.MethodWeldExecute;
import net.ent.etrs.commons.utils.cdiUtils.references.ConstantesCdi;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.spi.CDI;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * Propose les services permettant de lancer un conteneur Weld. Weld est
 * l'implémentation de référence de la spécification CDI (JSR-346).
 *
 * @author Christophe Louër
 */
public final class ContainerWeldCDI {

    private ContainerWeldCDI() {
    }

    /**
     * Lance le conteneur Weld.
     *
     * @return une instance du conteneur.
     */
    public static WeldContainer lancerContainerWeld() {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        return container;
    }

    /**
     * Arrête le conteneur Weld en paramètre.
     *
     * @param container
     */
    public static void arreterContainerWeld(final WeldContainer container) {
        if (Objects.nonNull(container) && container.isRunning()) {
            container.shutdown();
        }
    }

    /**
     * Exécute la méthode d'un bean dans le conteneur Weld. La méthode éxecutée doit
     * être annotée par @MethodWeldExecute .
     *
     * @param classe classe a exécuter dans le container Weld.
     */
    public static void executerClasse(Class<?> classe) {
        WeldContainer container = lancerContainerWeld();

        executerClasse(classe, container);

        arreterContainerWeld(container);
    }

    /**
     * Exécute la méthode d'un bean dans le conteneur Weld en paramètre. La méthode
     * éxecutée doit être annotée par @MethodWeldExecute .
     *
     * @param classe    classe a exécuter dans le container Weld.
     * @param container Instance du conteneur.
     */
    public static void executerClasse(final Class<?> classe, final WeldContainer container) {

        try {
            Optional<Method> methodeAnnotee = chercherMethodeAnnoteeMethodeWeldExecute(classe);
            if (!methodeAnnotee.isPresent()) {
                throw new NoSuchMethodException(ConstantesCdi.METHODE_A_EXECUTEE_NON_TROUVEE_EXCEPTION);
            }
            container.select(classe).get().getClass().getDeclaredMethod(methodeAnnotee.get().getName());
            Object o = container.select(classe).get();
            o.getClass().getMethod(methodeAnnotee.get().getName()).invoke(o);
//		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException e) {
        } catch (Exception e) {
            e.printStackTrace();
            arreterContainerWeld(container);
        }

    }

    /**
     * Recherche la méthode annotée @MethodWeldExecute .
     *
     * @param classe
     * @return la méthode cherchée encapsulée dans un objet Optional.
     */
    private static Optional<Method> chercherMethodeAnnoteeMethodeWeldExecute(Class<?> classe) {
        Method[] methodes = classe.getMethods();
        Method methodeCherchee = null;
        for (Method method : methodes) {
            for (Annotation a : method.getAnnotations()) {
                if (a.annotationType().getName().equals(MethodWeldExecute.class.getName())) {
                    methodeCherchee = method;
                }
            }
        }
        return Optional.ofNullable(methodeCherchee);
    }

    /**
     * Demande au container CDI une instance de la classe en paramètre.
     * La classe correspondante doit être définie avec un scope (@Dependant, @ApplicationScoped...).
     *
     * @param classe classe pour laquelle la méthode retourne une instance.
     * @return Instance demandée.
     */
    public static <T> T getInstanceOf(Class<T> classe) {
        return CDI.current().select(classe).get();
    }

}

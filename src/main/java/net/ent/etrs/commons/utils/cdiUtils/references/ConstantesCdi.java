package net.ent.etrs.commons.utils.cdiUtils.references;


import net.ent.etrs.commons.utils.cdiUtils.annotations.MethodWeldExecute;

public final class ConstantesCdi {

    public static final String EXECUTION_WELD_EXCEPTION = "Exécution de la classe imossible dans le context Weld";
    public static final String METHODE_A_EXECUTEE_NON_TROUVEE_EXCEPTION = String.format("Aucune méthode n'est annotée par %s", MethodWeldExecute.class.getSimpleName());

    private ConstantesCdi() {
    }

}

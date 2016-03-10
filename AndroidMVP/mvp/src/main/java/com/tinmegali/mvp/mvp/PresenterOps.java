package com.tinmegali.mvp.mvp;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 24/02/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali">github</a>
 * ---------------------------------------------------
 * Based on <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 * framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 * Dr. Douglas Schmidth</a>
 * ---------------------------------------------------
 */


/**
 * Interface Base que todos objetos Presenter precisam implementar.
 * Os métodos são chamados pela View e garantem uma sincronização
 * correta entre estes layers.
 *
 * @param <RequiredViewOps> interface contendo os métodos implementados
 *                         em View para comunicação com o Presenter
 *
 */
public interface PresenterOps<RequiredViewOps> {

    /**
     * Método chamado pela {@link GenericMVPActivity} para inicializar
     * um operação.
     * @param view  A instância atual
     */
    void onCreate(RequiredViewOps view);

    /**
     * Método disparado pela {@link GenericMVPActivity} para atualizar
     * as operação de View.
     * @param view  A instância atual
     */
    void onConfigurationChange(RequiredViewOps view);

    /**
     * Disparado na destruição dos objetos. Utilizado pelo Presenter
     * para manter um mapeamento correto do ciclo de vida dos objetos
     * @param isChangingConfiguration   Informa se a destruição é devida
     *                                  a uma mudança de configuração ou
     *                                  é absoluta
     */
    void onDestroy(boolean isChangingConfiguration);

    /**
     * Disparado pela {@link GenericMVPActivity} para informar um evento onBackPressed
     */
    void onBackPressed();
}

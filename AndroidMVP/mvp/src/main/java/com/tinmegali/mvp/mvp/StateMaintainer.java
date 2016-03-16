package com.tinmegali.mvp.mvp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * todo english comments
 * <p>Retains and maintain object's state between configuration changes
 * in Activitys and Fragments.
 * </p>
 * <p>
 * <strong>IMPORTANT: View Object should implement <code> RequiredViewOps </code></strong>
 * </p>
 * <p>
 * Created by Tin Megali on 24/02/16. <br>
 * Project: AndroidMVP <br>
 *
 * <a href="http://www.tinmegali.com">www.tinmegali.com</a>
 * </p>
 * --------------------------------------------------- <br>
 * <p>
 * Based on <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 *     framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 *     Dr. Douglas Schmidth</a>
 * </p>
 *
 * @see <a href="https://github.com/tinmegali/simple-mvp">Project's Git</a> <br>
 * @see <a href="https://github.com/tinmegali/simple-mvp/tree/master/AndroidMVP/app">Sample Application</a>
 * @see <a href="https://github.com/tinmegali/simple-mvp/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         Sample MVP interface
 *     </a>
 */
public class StateMaintainer {

    protected final String TAG = getClass().getSimpleName();

    private final String mStateMaintenerTag;
    private final WeakReference<FragmentManager> mFragmentManager;
    private StateMngFragment mStateMaintainerFrag;
    private boolean mIsRecreating;

    /**
     * Construtor
     * @param fragmentManager       repassa uma referência do FragmentManager
     * @param stateMaintainerTAG      a TAG utilizada para inserir o fragmento responsável
     *                              por manter os objetos "vivos"
     */
    public StateMaintainer(FragmentManager fragmentManager, String stateMaintainerTAG) {
        mFragmentManager = new WeakReference<>(fragmentManager);
        mStateMaintenerTag = stateMaintainerTAG;
    }

    /**
     * cria o fragmento responsável por armazenar o objetos
     * @return  true: criou o framentos e rodou pela primeira vez
     *          false: o objeto já foi criado, portanto é apenas recuperado
     */
    public boolean firstTimeIn() {
        try {
            // Recuperando referência
            mStateMaintainerFrag = (StateMngFragment)
                    mFragmentManager.get().findFragmentByTag(mStateMaintenerTag);

            // Criando novo RetainedFragment
            if (mStateMaintainerFrag == null) {
                Log.d(TAG, "Criando novo RetainedFragment " + mStateMaintenerTag);
                mStateMaintainerFrag = new StateMngFragment();
                mFragmentManager.get().beginTransaction()
                        .add(mStateMaintainerFrag, mStateMaintenerTag).commit();
                mIsRecreating = false;
                return true;
            } else {
                Log.d(TAG, "Retornando retained fragment existente " + mStateMaintenerTag);
                mIsRecreating = true;
                return false;
            }
        } catch (NullPointerException e) {
            Log.w(TAG, "Erro firstTimeIn()");
            return false;
        }
    }

    /**
     * Return <strong>true</strong> it the current Activity was recreated at least one time
     * @return  If the Activity was recreated
     */
    public boolean wasRecreated() { return mIsRecreating; }


    /**
     * Insere objeto a serem presenrvados durante mudanças de configuração
     * @param key   TAG de referência para recuperação do objeto
     * @param obj   Objeto a ser mantido
     */
    public void put(String key, Object obj) {
        mStateMaintainerFrag.put(key, obj);
    }

    /**
     * Insere objeto a serem presenrvados durante mudanças de configuração.
     * Utiliza a classe do Objeto como referência futura.
     * Só deve ser utilizado somente uma vez por classe, caso contrário haverá
     * possíveis conflitos na recuperação dos dados
     * @param obj   Objeto a ser mantido
     */
    public void put(Object obj) {
        put(obj.getClass().getName(), obj);
    }


    /**
     * Recupera o objeto salvo
     * @param key   Chave de referência do obj
     * @param <T>   tipo genérico de retorno
     * @return      Objeto armazenado
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key)  {
        return mStateMaintainerFrag.get(key);

    }

    /**
     * Verifica a existência de um objeto com a chave fornecida
     * @param key   Chave para verificação
     * @return      true: obj existe
     *              false: obj insexistente
     */
    public boolean hasKey(String key) {
        return mStateMaintainerFrag.get(key) != null;
    }


    /**
     * Armazena e administra os objetos que devem ser preservados
     * durante mudanças de configuração.
     * É instanciado somente uma vez e utiliza um
     * <code>HashMap</code> para salvar os objetos e suas
     * chaves de referência.
     */
    public static class StateMngFragment extends Fragment {
        private HashMap<String, Object> mData = new HashMap<>();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Garante que o Fragmento será preservado
            // durante mudanças de configuração
            setRetainInstance(true);
        }

        /**
         * Insere objetos no hashmap
         * @param key   Chave de referência
         * @param obj   Objeto a ser salvo
         */
        public void put(String key, Object obj) {
            mData.put(key, obj);
        }

        /**
         * Insere objeto utilizando o nome da classe como referência
         * @param object    Objeto a ser salvo
         */
        public void put(Object object) {
            put(object.getClass().getName(), object);
        }

        /**
         * Recupera objeto salvo no hashmap
         * @param key   Chave de referência
         * @param <T>   Classe
         * @return      Objeto salvo
         */
        @SuppressWarnings("unchecked")
        public <T> T get(String key) {
            return (T) mData.get(key);
        }
    }

}

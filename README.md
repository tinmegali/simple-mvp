<h1>Android-Model-View-Presenter-MVP</h1>
Framework canônico para aplicação do padrão Model View Presenter no Android

 Created by <a href="http://www.tinmegali.com">Tin Megali</a> on 24/02/16.
 Project: AndroidMVP
 
 Based on concept from <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 Dr. Douglas Schmidth</a> 
 ---------------------------------------------------
 
 <strong>Code comments in portuguese.</strong> For english you can check <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 the original rep.</a> I'll provide english comments later for my implementation.
 
 Código comentado em português
 
  <ul>
      <li>
          Crie as interfaces de comunicação entre os módulos View, Presenter e Model
           <ol>
               <li>
              interface <code>RequiredViewOps</code> fornece métodos para <code>Presenter</code>
          comunicar com <code>View</code>. É necessário extender <code>ActivityView</code>
               </li>
               <li>
                   interface <code>ProvidedPresenterOps</code> fornece operações oferecidas
                   ao layer View para comunicação com Presenter.
                   É preciso extender <code>PresenterOps<RequiredViewOps></code>
               </li>
               <li>
                   interface <code>RequiredPresenterOps</code> operações oferecidas
                   pelo layer Presenter para comunicações com Model
               </li>
               <li>
                   interface <code>ProvidedModelOps</code> operações oferecidos pelo
                   layer Model para comunicações com Presenter.
                   É preciso extender <code>ModelOps<RequiredPresenterOps></code>
               </li>
           </ol>
      </li>
      <li>
          Crie a classe <code>Model</code> extendendo <code>GenericModel<RequiredPresenterOps></code>
          e implementando <code>ProvidedModelOps</code>
          ex: {@link com.tinmegali.androidmvp.main.model.MainModel}
      </li>
      <li>
          Crie a classe <code>Presenter</code> extendendo <code>GenericPresenter</code>,
          implementando <code>RequiredPresenterOps</code> e <code>ProvidedPresenterOps</code>.
          exemplo: {@link com.tinmegali.androidmvp.main.presenter.MainPresenter}
      </li>
      <li>
          Crie a classe <code>View</code> GenericMVPActivity ou GenericMVPFragment e
          implementando <code>RequiredViewOps</code>
          Exemplo: {@link com.tinmegali.androidmvp.main.view.MainActivity}
      </li>.
 </ul>
 
<h2>Android MVP Class Diagram</h2>
<img src="http://www.tinmegali.com/wp-content/uploads/2016/02/mvp-class-diagram.jpg" />
 

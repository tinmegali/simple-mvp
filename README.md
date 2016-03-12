<h1>Android-Model-View-Presenter-MVP</h1>

<p>
A Model View Presenter Library using plain and simple canonical form,
based on concept from 
<a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc"> 
Dr. Douglas Schmidt</a>
</p>

<p>
Created by <a href="http://www.tinmegali.com">Tin Megali</a> 	
</p>

<h3>Learn more</h3>
<ul>
	<li>
		<a href="http://www.tinmegali.com/en/model-view-presenter-android-part-1/">
			Model View Presenter Tutorial series</a> <br />
		<em>Tutoriais também disponíveis em português</em>
	</li>
	<!-- 
<li>
		<a href="http://tinmegali.com/my-libs/simple-mvp/javadoc/">
			Using MVP library
		</a>
	</li>
 -->
	<li>
		<a href="http://tinmegali.com/my-libs/simple-mvp/javadoc/">
			JavaDoc
		</a>
	</li>
</ul>

<h3>Quick install</h3>
<ol>
	<li>
		add <em>build.gradle</em> <pre>compile 'com.tinmegali.mvp:mvp:0.0.4'</pre>
	</li>
	<li>
		Create interfaces to communicate between MVP layers
		<ul>
			<li>
				<code>interface RequiredViewOps extends ActivityView</code> 
				<br/>with VIEW methods to be accessed by PRESENTER
			</li>
			<li>
				<code>interface ProvidedPresenterOps extends PresenterOps<RequiredViewOps></code> 
				<br/>Operations offered to VIEW to communicate with PRESENTER
			</li>
			<li>
				<code>interface RequiredPresenterOps</code> 
				<br/>with Required PRESENTER methods available to MODEL
			</li>
			<li>
				<code>interface ProvidedModelOps extends ModelOps<RequiredPresenterOps></code> 
				<br/>Operations offered to MODEL to communicate with PRESENTER
			</li>
		</ul>
	</li>
	<li>
		Implement MVP objects extending its generics
		<ul>
			<li> MODEL from Model View Presenter (MVP) pattern. </br>
				<code>
					class MODEL 
						extends GenericModel<MVP_MainActivity.RequiredPresenterOps>
        implements MVP_MainActivity.ProvidedModelOps
        		</code>
			</li>
			
			<li>VIEW layer of MVP pattern <br/>
				<code>
					class VIEW_Activity extends GenericMVPActivity<MVP_MainActivity.RequiredViewOps,
						MVP_MainActivity.ProvidedPresenterOps,
                    	MainPresenter> 
                    implements MVP_MainActivity.RequiredViewOps
				</code> <br />
				<em>Could also extend <code>GenericMVPFragment</code></em>
			</li>
			
			<li>PRESENTER from Model View Presenter (MVP) Pattern. <br/>
				<code>
					class MainPresenter extends GenericPresenter<MVP_MainActivity.RequiredPresenterOps,
						MVP_MainActivity.ProvidedModelOps, MVP_MainActivity.RequiredViewOps, MainModel>
					implements
            			MVP_MainActivity.RequiredPresenterOps,
            			MVP_MainActivity.ProvidedPresenterOps
				</code>
			</li>
			
		</ul>
	</li>
</ol>

<h2>Instalação rápida</h2> 
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
 

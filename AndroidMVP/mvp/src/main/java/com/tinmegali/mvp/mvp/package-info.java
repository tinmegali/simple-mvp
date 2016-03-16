/**
 * <p>
 *     github.com/tinmegali/simple-mvp
 * </p>
 *
 * Provides the necessary classes to implement
 * a Model View Presenter architecture pattern.
 * <p>
 * Each layer of MVP pattern should extends
 * its generic object and implement the correct
 * interfaces to communicate with the other Layers. <br>
 * <strong> Check sample MVP interface
 *     <a href="https://github.com/tinmegali/simple-mvp/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         sample interface
 *     </a>
 * </strong>
 * <ul>
 *     <li><strong>VIEW</strong>
 *     <ul>
 *         <li>
 *             extends {@link com.tinmegali.mvp.mvp.GenericMVPActivity} or
 *             {@link com.tinmegali.mvp.mvp.GenericMVPFragment}
 *         </li>
 *         <li>
 *             implements <code>RequiredViewOps</code>: a interface that you create
 *             containing VIEW operations
 *             to be accessed by the PRESENTER
 *         </li>
 *     </ul>
 *
 *     </li>
 *     <li>
 *         <strong>MODEL</strong>
 *         <ul>
 *         <li>
 *             extends {@link com.tinmegali.mvp.mvp.GenericModel}
 *         </li>
 *         <li>
 *             implements <code>ProvidedModelOps</code>: a interface that you create
 *             containing MODEL operations
 *             to be accessed by the PRESENTER
 *         </li>
 *
 *          </ul>
 *
 *     </li>
 *     <li>
 *         <strong>PRESENTER</strong>
 *         <ul>
 *         <li>
 *             extends {@link com.tinmegali.mvp.mvp.GenericPresenter}
 *         </li>
 *         <li>
 *             implements <code>RequiredPresenterOps</code>: a interface that you create
 *             containing PRESENTER operations
 *             to be accessed by the VIEW
 *         </li>
 *         <li>
 *             implements <code>ProvidedPresenterOps</code>: a interface that you create
 *             containing PRESENTER operations
 *             to be accessed by the MODEL
 *         </li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * @see <a href="https://github.com/tinmegali/simple-mvp">Project's Git</a> <br>
 * @see <a href="https://github.com/tinmegali/simple-mvp/tree/master/AndroidMVP/app">Sample Application</a>
 * @see <a href="https://github.com/tinmegali/simple-mvp/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         Sample MVP interface
 *     </a>
 *
 *     <p>
 *         Copyright 2016 Tin Megali

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 *     </p>
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
 */
package com.tinmegali.mvp.mvp;
/**
 * Provides the necessary classes to implement
 * a Model View Presenter architecture pattern.
 * <p>
 * Each layer of MVP pattern should extends
 * its generic object and implement the correct
 * interfaces to communicate with the other Layers. <br />
 * <strong> Check sample MVP interface
 *     <a href="https://github.com/tinmegali/Android-Model-View-Presenter-MVP/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
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
 *     </ul>
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
 *     </li>
 * </ul>
 *
 * @see <a href="https://github.com/tinmegali/Android-Model-View-Presenter-MVP">Project's Git</a> <br />
 * @see <a href="https://github.com/tinmegali/Android-Model-View-Presenter-MVP/tree/master/AndroidMVP/app">Sample Application</a>
 * @see <a href="https://github.com/tinmegali/Android-Model-View-Presenter-MVP/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         Sample MVP interface
 *     </a>
 *
 */
package com.tinmegali.mvp.mvp;
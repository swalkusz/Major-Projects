package sapper.windows;

import javax.swing.*;

public class Fail {
    public Fail(Game game) {
        int option = JOptionPane.showConfirmDialog(null, "Boom!!! You failed! Try again!",
                "Sapper - failed", JOptionPane.YES_NO_OPTION);
        if (option == 0)
            tryAgain();
        game.dispose();
    }

    private void tryAgain() {
        new Startup();
    }
}

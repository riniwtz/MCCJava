package io.github.riniwtz.commands;

public class HelpCommand extends AbstractBaseCommand {
    private boolean isCountHasCharacter = false;
    private long countValue;

    enum CommandArguments {
        TARGET_ARGUMENT("<targets>"),
        ITEM_ARGUMENT("<item>"),
        COUNT_ARGUMENT("[<count>]");

        private String argumentPlaceholder;

        CommandArguments(String argumentPlaceholder) {
            this.argumentPlaceholder = argumentPlaceholder;
        }

        private String getPlaceholder() {
            return argumentPlaceholder;
        }
    }

    private long getConvertNumberToLong(String number) {
        try {
            countValue = Long.parseLong(number);
        } catch (NumberFormatException e) {
            this.isCountHasCharacter = true;
        }
        return countValue;
    }

    private boolean isCountValid() {
        return (!isCountHasCharacter) && (countValue != 0) && (countValue <= Integer.MAX_VALUE) && (countValue >= 0);
    }

    private void runGiveHelpOutput(String[] cmd) {
        String command;
        String target;
        String item;
        if (cmd.length == 2) {
            command = cmd[1];
            CommandOutputMessage.printHelpCommandMessageOutput(command,
                    CommandArguments.TARGET_ARGUMENT.getPlaceholder(),
                    CommandArguments.ITEM_ARGUMENT.getPlaceholder(),
                    CommandArguments.COUNT_ARGUMENT.getPlaceholder()
            );
        }
        if (cmd.length == 3) {
            command = cmd[1];
            target = cmd[2];
            CommandOutputMessage.printHelpCommandMessageOutput(command, target,
                    CommandArguments.ITEM_ARGUMENT.getPlaceholder(),
                    CommandArguments.COUNT_ARGUMENT.getPlaceholder());
        }
        if ((cmd.length == 4) || (cmd.length > 5)) {
            command = cmd[1];
            target = cmd[2];
            item = cmd[3];

            if (block.exists(item) || this.item.exists(item))
                CommandOutputMessage.printHelpCommandMessageOutput(command, target, item,
                        CommandArguments.COUNT_ARGUMENT.getPlaceholder());
            else {
                StringBuilder out = new StringBuilder();
                for (String targetName : cmd) out.append(targetName).append(" ");
                target = out.substring(out.indexOf(command) + command.length() + 1, out.length() - 1);
                CommandOutputMessage.printHelpCommandMessageOutput(command, target, 
                        CommandArguments.ITEM_ARGUMENT.getPlaceholder(),
                        CommandArguments.COUNT_ARGUMENT.getPlaceholder());
            }
        }
        if (cmd.length == 5) {
            command = cmd[1];
            target = cmd[2];
            countValue = getConvertNumberToLong(cmd[4]);
            if (!isCountValid()) {
                StringBuilder out = new StringBuilder();
                for (String itemName : cmd) out.append(itemName).append(" ");
                item = out.substring(out.indexOf(command) + command.length() + 1, out.length() - 1);
                CommandOutputMessage.printHelpCommandMessageOutput(command, target, item,
                        CommandArguments.COUNT_ARGUMENT.getPlaceholder());
            }
            countValue = 1;
        }
        isCountHasCharacter = false;
    }

    @Override
    public void execute(String[] cmd) {
        if (cmd.length == 1) CommandOutputMessage.printHelpCommandListMessageOutput();
        switch (cmd[1]) {
            case "give" -> runGiveHelpOutput(cmd);
            case "" -> {}
            default -> CommandOutputMessage.printCommandInsufficientPermissionsMessageOutput();
        }
    }

    @Override
    protected boolean hasCommandHandlerError(String[] cmd) {
        return false;
    }
}

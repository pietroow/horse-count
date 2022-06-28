package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HorseCountService {

    private static final String HORSE_SOUND = "neigh";
    private List<Integer> usedCharPosition = new ArrayList<>();
    private AtomicInteger totalHorses = new AtomicInteger(0);

    public Integer countHorses(String userInput) {
        initiateCountAttempt(userInput);
        return totalHorses.get();
    }

    private void initiateCountAttempt(String userInput) {
        if (isValidCount(userInput)) {
            totalHorses.getAndIncrement();
            initiateCountAttempt(userInput);
        }
    }

    private boolean isValidCount(String userInput) {
        int totalCharsUsedBeforeVerification = usedCharPosition.size();
        for (char letter : HORSE_SOUND.toCharArray()) {

            Integer positionFirstLetter = getPositionFirstLetterAvailable(letter, userInput);

            // IN CASE OF DO NOT FIND ANY AVAILABLE LETTER TO VALIDATE. FINISH THE EXECUTION
            if (positionFirstLetter == null) {
                return false;
            }

            usedCharPosition.add(positionFirstLetter);
        }
        int totalCharsUsedAfterVerification = usedCharPosition.size();
        return checkIfCountIsValid(totalCharsUsedBeforeVerification, totalCharsUsedAfterVerification);
    }

    // MAKING SURE WE GOT ALL THE LETTERS TO WRITE THE WORD
    private boolean checkIfCountIsValid(int totalCharsUsedBeforeVerification, int totalCharsUsedAfterVerification) {
        return (totalCharsUsedAfterVerification > totalCharsUsedBeforeVerification)
                && ((totalCharsUsedAfterVerification % HORSE_SOUND.length()) == 0);
    }

    private Integer getPositionFirstLetterAvailable(char letter, String userInput) {
        char[] chars = userInput.toCharArray();
        int lastAdded = getLastAddedChar();

        for (int i = 0; i < chars.length; i++) {
            if (isSameLetter(letter, chars[i])
                    && isPositionNotBeingUsed(i)
                    && (isLookingForFirstChar(letter) || isHigherThanLastAdded(lastAdded, i))) {
                return i;
            }
        }

        //IF DOESN'T FIND ANY AVAILABLE, RETURN NULL. IT WILL BE WHAT DECIDE WHEN THE CODE WILL STOP TO EXECUTE
        return null;
    }

    private boolean isPositionNotBeingUsed(int index) {
        return !isPositionAlreadyTaken(index);
    }

    private boolean isSameLetter(char letter, char chars) {
        return chars == letter;
    }

    private int getLastAddedChar() {
        return usedCharPosition.isEmpty()
                ? 0
                : usedCharPosition.get(usedCharPosition.size() - 1);
    }

    private boolean isLookingForFirstChar(char letter) {
        char firstLetterToFind = HORSE_SOUND.toCharArray()[0];
        return letter == firstLetterToFind;
    }

    private boolean isHigherThanLastAdded(int lastAdded, int i) {
        return i > lastAdded;
    }

    private boolean isPositionAlreadyTaken(int index) {
        return usedCharPosition.stream()
                .anyMatch(position -> position.equals(index));
    }

}
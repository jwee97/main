package seedu.address.model.listmanagers.packinglistitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.uniquelist.UniqueList;
import seedu.address.model.util.uniquelist.exceptions.DuplicateElementException;
import seedu.address.model.util.uniquelist.exceptions.ElementNotFoundException;

class UniquePackingListTest {

    //They have a personBuilder, but since we dont have, improvise by using this for now
    public static final PackingListItem FIXED_PACKING_LIST_ITEM_PANTS =
            new PackingListItem(new ItemName("Pants"), new Quantity(5),
                    false);

    public static final PackingListItem FIXED_PACKING_LIST_ITEM_SHIRT =
            new PackingListItem(new ItemName("Shirt"), new Quantity(3),
                    false);

    private static final PackingListItem FIXED_PACKING_LIST_ITEM_JEANS =
            new PackingListItem(new ItemName("Jeans"), new Quantity(10),
                    true);

    private final UniqueList<PackingListItem> uniquePackingList = new UniqueList<>();

    @Test
    public void containsNullPackingListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList.contains(null));
    }

    @Test
    public void containsPackingListNotInListReturnsFalse() {
        assertFalse(uniquePackingList.contains(FIXED_PACKING_LIST_ITEM_JEANS));
    }

    @Test
    public void containsPackingListInListReturnsTrue() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        assertTrue(uniquePackingList.contains(FIXED_PACKING_LIST_ITEM_JEANS));
    }

    @Test
    public void containsPackingListWithSameIdentifyFieldsInListReturnsTrue() {
        //Slightly different from UniquePersonListTest, due to different
        //contains convention used for AB3 and for ours.
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        PackingListItem packingListItem = new PackingListItem(new ItemName("Jeans"),
                new Quantity(10), false);
        assertTrue(uniquePackingList.contains(packingListItem));
    }

    @Test
    public void addNullPackingListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList.add(null));
    }

    @Test
    public void addDuplicatePackingListThrowsDuplicatePackingListException() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        assertThrows(
                DuplicateElementException.class, () -> uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS));
    }

    @Test
    public void setPackingListNullTargetPackingListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList.setElement(null,
                FIXED_PACKING_LIST_ITEM_JEANS));
    }

    @Test
    public void setPackingListNullPackingListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList
                .setElement(FIXED_PACKING_LIST_ITEM_JEANS,
                        null));
    }

    @Test
    public void setPackingListTargetPackingListNotInListThrowsPackingListNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniquePackingList.setElement(
                FIXED_PACKING_LIST_ITEM_JEANS, FIXED_PACKING_LIST_ITEM_JEANS));
    }

    @Test
    public void setPackingListEditedPackingListIsSamePackingListSuccess() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        uniquePackingList.setElement(FIXED_PACKING_LIST_ITEM_JEANS, FIXED_PACKING_LIST_ITEM_JEANS);
        UniqueList<PackingListItem> expectedPackingList = new UniqueList<>();
        expectedPackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        assertEquals(expectedPackingList, uniquePackingList);
    }

    @Test
    public void setPackingListHasSameIdentitySuccess() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        PackingListItem packingListItem = new PackingListItem(new ItemName("Jeans"), new Quantity(10),
                true);
        uniquePackingList.setElement(FIXED_PACKING_LIST_ITEM_JEANS, packingListItem);
        UniqueList<PackingListItem> expectedUniquePackingList = new UniqueList<>();
        expectedUniquePackingList.add(packingListItem);
        assertEquals(expectedUniquePackingList, uniquePackingList);
    }

    @Test
    public void setPackingListEditedPackingListHasDifferentPackingListSuccess() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        uniquePackingList.setElement(FIXED_PACKING_LIST_ITEM_JEANS, FIXED_PACKING_LIST_ITEM_PANTS);
        UniqueList<PackingListItem> expectedPackingExpenseList = new UniqueList<>();
        expectedPackingExpenseList.add(FIXED_PACKING_LIST_ITEM_PANTS);
        assertEquals(expectedPackingExpenseList, uniquePackingList);
    }

    @Test
    public void setPackingListEditedPackingListHasNonUniqueIdentityThrowsDuplicatePackingListException() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_PANTS);
        assertThrows(DuplicateElementException.class, () -> uniquePackingList.setElement(
                FIXED_PACKING_LIST_ITEM_JEANS, FIXED_PACKING_LIST_ITEM_PANTS));
    }

    @Test
    public void removeNullPackingListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList.remove(null));
    }

    @Test
    public void removePackingListDoesNotExistThrowsPackingListNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniquePackingList
                .remove(FIXED_PACKING_LIST_ITEM_SHIRT));
    }

    @Test
    public void removeExistingPackingListRemovesPackingList() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        uniquePackingList.remove(FIXED_PACKING_LIST_ITEM_JEANS);
        UniqueList<PackingListItem> expectedPackingList = new UniqueList<>();
        assertEquals(expectedPackingList, uniquePackingList);
    }

    @Test
    public void setPackingListNullUniquePackingListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList
                .setElements((UniqueList<PackingListItem>) null));
    }

    @Test
    public void setPackingListUniquePackingListReplacesOwnListWithProvidedUniquePackingList() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_SHIRT);
        UniqueList<PackingListItem> expectedUniquePackingList = new UniqueList<>();
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        uniquePackingList.setElements(expectedUniquePackingList);
        assertEquals(expectedUniquePackingList, uniquePackingList);
    }

    @Test
    public void setUniquePackingListNullListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList
                .setElements((List<PackingListItem>) null));
    }

    @Test
    public void setUniquePackingListListReplacesOwnListWithProvidedList() {
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        List<PackingListItem> packingList = Collections.singletonList(FIXED_PACKING_LIST_ITEM_SHIRT);
        uniquePackingList.setElements(packingList);
        UniqueList<PackingListItem> expectedPackingList = new UniqueList<>();
        expectedPackingList.add(FIXED_PACKING_LIST_ITEM_SHIRT);
        assertEquals(expectedPackingList, uniquePackingList);
    }

    @Test
    public void setPackingListListWithDuplicateFixedExpenseThrowsDuplicateItemException() {
        List<PackingListItem> listWithDuplicatePackingListItems = Arrays
                .asList(FIXED_PACKING_LIST_ITEM_JEANS, FIXED_PACKING_LIST_ITEM_JEANS);
        assertThrows(DuplicateElementException.class, () ->
                uniquePackingList.setElements(listWithDuplicatePackingListItems));
    }

    @Test
    public void asUnmodifiableObservableListModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniquePackingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void testEquals() {
        UniqueList<PackingListItem> expectedUniquePackingLists = new UniqueList<>();
        expectedUniquePackingLists.add(FIXED_PACKING_LIST_ITEM_JEANS);
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        assertEquals(uniquePackingList, expectedUniquePackingLists);

    }

    @Test
    public void testHashCode() {
        //Same Hash Code
        uniquePackingList.add(FIXED_PACKING_LIST_ITEM_JEANS);
        UniqueList<PackingListItem> pl = new UniqueList<>();
        pl.add(FIXED_PACKING_LIST_ITEM_JEANS);
        assertEquals(pl.hashCode(), uniquePackingList.hashCode());

        //Different Hash code
        UniqueList<PackingListItem> diffPl = new UniqueList<>();
        diffPl.add(FIXED_PACKING_LIST_ITEM_SHIRT);
        assertNotEquals(diffPl.hashCode(), uniquePackingList.hashCode());
    }
}

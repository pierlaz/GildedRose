package fi.oulu.tol.sqat.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {
	int counter;
	Item agedBrie;
	Item elisir;
	Item sulfuras;
	Item backstage;
	Item cake;
	public List<Item> items = null;

	@Before
	public void setUp() {
		items = new ArrayList<Item>();
		GildedRose.items = items;
		agedBrie = new Item("Aged Brie", 7, 1);
		elisir = new Item("Elisir of the Dragon", -1, 2);
		sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
		cake = new Item("Conjured Mana Cake", 0, 6);

	}

	@Test
	public void shopWithOneItemThatIsCakeAndDateIsPassed() {
		items.add(cake);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), -1);
		assertEquals(items.get(counter).getQuality(), 4);
		items.remove(cake);
	}

	@Test
	public void shopWithOneItemThatIsAgedBrie() {
		items.add(agedBrie);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), 6);
		assertEquals(items.get(counter).getQuality(), 2);
		items.remove(agedBrie);
	}

	@Test
	public void shopWithOneItemThatIsElisirWithNoNegativeQuality() {
		items.add(elisir);
		GildedRose.updateQuality();
		GildedRose.updateQuality();
		GildedRose.updateQuality();
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), -5);
		assertEquals(items.get(counter).getQuality(), 0);
		items.remove(elisir);
	}

	@Test
	public void shopWithOneItemThatIsSulfuras() {
		items.add(sulfuras);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), 0);
		assertEquals(items.get(counter).getQuality(), 80);
		items.remove(sulfuras);
	}

	@Test
	public void shopWithOneItemThatIsSulfurasWithSellInUnder0() {
		sulfuras = new Item("Sulfuras, Hand of Ragnaros", -5, 80);
		items.add(sulfuras);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(),-5);
		assertEquals(items.get(counter).getQuality(), 80);
		items.remove(sulfuras);
	}

	@Test
	public void shopWithOneItemThatIsNotSulfurasButItemWithQuality60() {
		agedBrie = new Item("Aged Brie", -1, 48);
		items.add(agedBrie);
		GildedRose.updateQuality();
		GildedRose.updateQuality();
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), -4);
		assertEquals(items.get(counter).getQuality(), 50);
		items.remove(0);
	}

	@Test
	public void shopWithOneItemThatIsBackstageWith4DaysLeftWithQuality55() {
		backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 49);
		items.add(backstage);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), 3);
		assertEquals(items.get(counter).getQuality(), 50);
		items.remove(backstage);
	}

	@Test
	public void shopWithOneItemThatIsBackstageWith4DaysLeft() {
		backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 20);
		items.add(backstage);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getQuality(), 23);
		assertEquals(items.get(counter).getSellIn(), 3);
		items.remove(backstage);
	}

	@Test
	public void shopWithOneItemThatIsBackstageWith9DaysLeftWithQuality55() {
		backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 8, 49);
		items.add(backstage);
		GildedRose.updateQuality();
		GildedRose.updateQuality();
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), 5);
		assertEquals(items.get(counter).getQuality(), 50);
		items.remove(backstage);
	}

	@Test
	public void shopWithOneItemThatIsBackstageWith9DaysLeft() {
		backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 20);
		items.add(backstage);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), 8);
		assertEquals(items.get(counter).getQuality(), 22);
		items.remove(backstage);
	}

	@Test
	public void shopWithOneItemThatIsBackstageWith15DaysLeft() {
		backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 9);
		items.add(backstage);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), 14);
		assertEquals(items.get(counter).getQuality(), 10);
		items.remove(backstage);
	}

	@Test
	public void shopWithOneItemThatIsBackstageWithConcertPassed() {
		backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 9);
		items.add(backstage);
		GildedRose.updateQuality();
		assertEquals(items.get(counter).getSellIn(), -1);
		assertEquals(items.get(counter).getQuality(), 0);
		items.remove(backstage);
	}
}

package hu.bme.mit.spaceship;

import static junit.framework.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GT4500Test {

    private GT4500 ship;

    private TorpedoStore mockTorpedoStore1;
    private TorpedoStore mockTorpedoStore2;

    @BeforeEach
    public void init() {
        this.mockTorpedoStore1 = mock(TorpedoStore.class);
        this.mockTorpedoStore2 = mock(TorpedoStore.class);
        this.ship = new GT4500(
                this.mockTorpedoStore1,
                this.mockTorpedoStore2
        );
    }

    @Test
    public void fireTorpedo_Single_First_Fire_Success() {
        // Arrange
        when(mockTorpedoStore1.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertTrue(result);
        verify(mockTorpedoStore1, times(1)).isEmpty();
        verify(mockTorpedoStore2, times(0)).isEmpty();
        verify(mockTorpedoStore1, times(1)).fire(1);
        verify(mockTorpedoStore2, times(0)).fire(1);
    }

    @Test
    public void fireTorpedo_Single_First_Fire_Success_Primary_Empty() {
        // Arrange
        when(mockTorpedoStore1.isEmpty()).thenReturn(true);
        when(mockTorpedoStore2.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertTrue(result);
        verify(mockTorpedoStore1, times(1)).isEmpty();
        verify(mockTorpedoStore2, times(1)).isEmpty();
        verify(mockTorpedoStore1, times(0)).fire(1);
        verify(mockTorpedoStore2, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_Single_Second_Fire_Success() {
        // Arrange
        when(mockTorpedoStore1.fire(1)).thenReturn(true);
        when(mockTorpedoStore2.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);
        boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertTrue(result);
        assertTrue(result2);
        verify(mockTorpedoStore1, times(1)).isEmpty();
        verify(mockTorpedoStore2, times(1)).isEmpty();
        verify(mockTorpedoStore1, times(1)).fire(1);
        verify(mockTorpedoStore2, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_Single_Third_Fire_Success() {
        // Arrange
        when(mockTorpedoStore1.fire(1)).thenReturn(true);
        when(mockTorpedoStore2.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);
        boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
        boolean result3 = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertTrue(result);
        assertTrue(result2);
        assertTrue(result3);
        verify(mockTorpedoStore1, times(2)).isEmpty();
        verify(mockTorpedoStore2, times(1)).isEmpty();
        verify(mockTorpedoStore1, times(2)).fire(1);
        verify(mockTorpedoStore2, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_Single_First_Fire_Both_Stores_Empty_Fail() {
        // Arrange
        when(mockTorpedoStore1.isEmpty()).thenReturn(true);
        when(mockTorpedoStore2.isEmpty()).thenReturn(true);
        when(mockTorpedoStore1.fire(1)).thenReturn(true);
        when(mockTorpedoStore2.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertFalse(result);
        verify(mockTorpedoStore1, times(1)).isEmpty();
        verify(mockTorpedoStore2, times(1)).isEmpty();
        verify(mockTorpedoStore1, times(0)).fire(1);
        verify(mockTorpedoStore2, times(0)).fire(1);
    }

    @Test
    public void fireTorpedo_All_Success() {
        // Arrange
        when(mockTorpedoStore1.fire(1)).thenReturn(true);
        when(mockTorpedoStore2.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertTrue(result);
        verify(mockTorpedoStore1, times(1)).isEmpty();
        verify(mockTorpedoStore2, times(1)).isEmpty();
        verify(mockTorpedoStore1, times(1)).fire(1);
        verify(mockTorpedoStore2, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_All_Primary_Empty() {
        // Arrange
        when(mockTorpedoStore1.isEmpty()).thenReturn(true);
        when(mockTorpedoStore1.fire(1)).thenReturn(true);
        when(mockTorpedoStore2.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertTrue(result);
        verify(mockTorpedoStore1, times(1)).isEmpty();
        verify(mockTorpedoStore2, times(1)).isEmpty();
        verify(mockTorpedoStore1, times(0)).fire(1);
        verify(mockTorpedoStore2, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_All_Secondary_Empty() {
        // Arrange
        when(mockTorpedoStore2.isEmpty()).thenReturn(true);
        when(mockTorpedoStore1.fire(1)).thenReturn(true);
        when(mockTorpedoStore2.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertTrue(result);
        verify(mockTorpedoStore1, times(1)).isEmpty();
        verify(mockTorpedoStore2, times(1)).isEmpty();
        verify(mockTorpedoStore1, times(1)).fire(1);
        verify(mockTorpedoStore2, times(0)).fire(1);
    }

    @Test
    public void fireTorpedo_All_Both_Stores_Empty() {
        // Arrange
        when(mockTorpedoStore1.isEmpty()).thenReturn(true);
        when(mockTorpedoStore2.isEmpty()).thenReturn(true);
        when(mockTorpedoStore1.fire(1)).thenReturn(true);
        when(mockTorpedoStore2.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertFalse(result);
        verify(mockTorpedoStore1, times(1)).isEmpty();
        verify(mockTorpedoStore2, times(1)).isEmpty();
        verify(mockTorpedoStore1, times(0)).fire(1);
        verify(mockTorpedoStore2, times(0)).fire(1);
    }

}

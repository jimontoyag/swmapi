package com.jimontoyag;

import com.jimontoyag.service.SWAService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  private SWAService swaService;

  @Before
  void setup() {
    swaService = mock(SWAService.class);
  }

  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }
}

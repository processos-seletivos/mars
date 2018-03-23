package com.contaazul.mars;

import com.contaazul.mars.robot.command.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarsCommandParserTests {

	@Test
	public void readCommandMarch() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsCommandParser marsCommandParser = new MarsCommandParser();
		//when
		List<Command> decodedCommands = marsCommandParser.decodeCommandString("M");
		//then
		assertThat(decodedCommands.size()).isEqualTo(1);
		assertThat(decodedCommands.get(0).getClass()).isEqualTo(MarchCommand.class);

	}

	@Test
	public void readCommandRight() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsCommandParser marsCommandParser = new MarsCommandParser();
		//when
		List<Command> decodedCommands = marsCommandParser.decodeCommandString("R");
		//then
		assertThat(decodedCommands.size()).isEqualTo(1);
		assertThat(decodedCommands.get(0).getClass()).isEqualTo(RightCommand.class);

	}

	@Test
	public void readCommandLeft() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsCommandParser marsCommandParser = new MarsCommandParser();
		//when
		List<Command> decodedCommands = marsCommandParser.decodeCommandString("L");
		//then
		assertThat(decodedCommands.size()).isEqualTo(1);
		assertThat(decodedCommands.get(0).getClass()).isEqualTo(LeftCommand.class);

	}

	@Test(expected = InvalidCommandException.class)
	public void readInexistentCommand() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsCommandParser marsCommandParser = new MarsCommandParser();
		//when
		List<Command> decodedCommands = marsCommandParser.decodeCommandString("A");

	}

	@Test
	public void readCommandSequence() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsCommandParser marsCommandParser = new MarsCommandParser();
		//when
		List<Command> decodedCommands = marsCommandParser.decodeCommandString("RLM");
		//then
		assertThat(decodedCommands.size()).isEqualTo(3);
		assertThat(decodedCommands.get(0).getClass()).isEqualTo(RightCommand.class);
		assertThat(decodedCommands.get(1).getClass()).isEqualTo(LeftCommand.class);
		assertThat(decodedCommands.get(2).getClass()).isEqualTo(MarchCommand.class);

	}

	@Test
	public void readEmptyCommandSequence() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsCommandParser marsCommandParser = new MarsCommandParser();
		//when
		List<Command> decodedCommands = marsCommandParser.decodeCommandString(null);
		//then
		assertThat(decodedCommands.size()).isEqualTo(0);

	}

}

package rebound.datashets.impls.localfile.formats.gds.cbor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import rebound.dataformats.cbor.CBORUtilities;
import rebound.datashets.api.model.DatashetsTable;
import rebound.datashets.impls.helpers.gds.DatashetsGDSTranscoder;
import rebound.datashets.impls.localfile.DatashetsLocalFileFormatTranscoder;
import rebound.exceptions.BinarySyntaxException;
import rebound.exceptions.GenericDataStructuresFormatException;

public enum DatashetsLocalFileFormatTranscoderForCBOR
implements DatashetsLocalFileFormatTranscoder
{
	I;
	
	
	@Override
	public DatashetsTable read(InputStream in) throws IOException, BinarySyntaxException
	{
		Object gds = CBORUtilities.parseCBOR(in);
		
		try
		{
			return DatashetsGDSTranscoder.decode(gds);
		}
		catch (GenericDataStructuresFormatException exc)
		{
			throw BinarySyntaxException.inst(exc);
		}
	}
	
	
	@Override
	public void write(DatashetsTable data, OutputStream out) throws IOException
	{
		Object gds = DatashetsGDSTranscoder.encode(data);
		CBORUtilities.serializeCBORToStream(gds, out);
	}
}

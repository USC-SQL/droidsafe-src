/*
 * Copyright (C) 2015,  Massachusetts Institute of Technology
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Please email droidsafe@lists.csail.mit.edu if you need additional
 * information or have any questions.
 */

package droidsafe.speclang;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import soot.RefType;

public class StringValue extends ConcreteArgumentValue {
	protected String value;

	public StringValue(String value) {
		super();
		this.value = value;
		escape();
	}

	@Override
	public String toStringValue() {
		return "STRING: " + value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		escape();
	}

	public String toString() {
		return "\"" + value + "\"";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringValue other = (StringValue) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
	
	
	public void escape() {
		 final StringBuilder result = new StringBuilder();
		 for (char character : value.toCharArray() ){
	       if (character == '"') {
	         result.append("\\\"");
	       } else result.append(character);
	     }
	     value = result.toString();
	}
}

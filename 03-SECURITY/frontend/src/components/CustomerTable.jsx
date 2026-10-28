import { useEffect, useState } from "react";
import api from "../api/axios";
import {
    Table, TableBody, TableCell, TableHead, TableRow,
    Button, TextField
} from "@mui/material";
import CustomerModal from "./CustomerModal";
import { useSnackbar } from "notistack";

function CustomerTable() {

    const [data, setData] = useState([]);
    const [search, setSearch] = useState("");
    const [open, setOpen] = useState(false);
    const [edit, setEdit] = useState(null);
    const { enqueueSnackbar } = useSnackbar();
        
//this function pulls data from the the backend api
    const fetchData = async () => {
        const res = await api.get("/api/students");
        setData(res.data);
        };
        
    useEffect(() => { fetchData(); }, []);

    const handleSave = async (form) => {
        try {
        if (edit) {
            await api.put(`/api/students/${edit.id}`, form);
            enqueueSnackbar("Updated successfully", { variant: "success" });
        } else {
            await api.post("/api/students", form);
            enqueueSnackbar("Created successfully", { variant: "success" });
        }
        setOpen(false);
        setEdit(null);
        fetchData();
        } catch {
        enqueueSnackbar("Operation failed", { variant: "error" });
        }
    };

    const handleDelete = async (id) => {
        try {
        await api.delete(`/api/students/${id}`);
        enqueueSnackbar("Deleted", { variant: "success" });
        fetchData();
        } catch {
        enqueueSnackbar("Delete failed", { variant: "error" });
        }
    };

    const filtered = data.filter((c) =>
        c.firstName?.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <>
        <TextField label="Search" onChange={(e) => setSearch(e.target.value)} />
        <Button onClick={() => setOpen(true)}>Add Student</Button>

        <Table>
            <TableHead>
            <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>First Name</TableCell>
                <TableCell>Last Name</TableCell>
                <TableCell>Age</TableCell>
                <TableCell>Department</TableCell>
                <TableCell>CGPA</TableCell>
                <TableCell>Actions</TableCell>
            </TableRow>
            </TableHead>
            <TableBody>
            {filtered.map((c) => (
                <TableRow key={c.id}>
                <TableCell>{c.id}</TableCell>
                <TableCell>{c.firstName}</TableCell>
                <TableCell>{c.lastName}</TableCell>
                <TableCell>{c.age}</TableCell>
                <TableCell>{c.department}</TableCell>
                <TableCell>{c.cgpa}</TableCell>
                <TableCell>
                    <Button onClick={() => { setEdit(c); setOpen(true); }}>Edit</Button>
                    <Button color="error" onClick={() => handleDelete(c.id)}>Delete</Button>
                </TableCell>
                </TableRow>
            ))}
            </TableBody>
        </Table>

        <CustomerModal
            open={open}
            handleClose={() => setOpen(false)}
            handleSave={handleSave}
            editData={edit}
        />
        </>
    );
}

export default CustomerTable
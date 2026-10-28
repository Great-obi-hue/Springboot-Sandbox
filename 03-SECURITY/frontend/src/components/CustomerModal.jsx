import { Dialog, DialogTitle, DialogContent, TextField, Button } from "@mui/material";
import { useState, useEffect } from "react";

export default function CustomerModal({ open, handleClose, handleSave, editData }) {
    const [form, setForm] = useState({ firstName: "", lastName: "", age: "", department: "", cgpa: "" });

    useEffect(() => {
        if (editData) setForm(editData);
    }, [editData]);

    return (
        <Dialog open={open} onClose={handleClose}>
        <DialogTitle>{editData ? "Edit" : "Create"} Student</DialogTitle>
        <DialogContent>
            <TextField fullWidth label="First Name" sx={{ mt: 2 }} value={form.firstName}
            onChange={(e) => setForm({ ...form, firstName: e.target.value })} />
            <TextField fullWidth label="Last Name" sx={{ mt: 2 }} value={form.lastName}
            onChange={(e) => setForm({ ...form, lastName: e.target.value })} />
            <TextField fullWidth label="Age" sx={{ mt: 2 }} value={form.age}
            onChange={(e) => setForm({ ...form, age: e.target.value })} />
            <TextField fullWidth label="Department" sx={{ mt: 2 }} value={form.department}
            onChange={(e) => setForm({ ...form, department: e.target.value })} />
            <TextField fullWidth label="CGPA" sx={{ mt: 2 }} value={form.cgpa}
            onChange={(e) => setForm({ ...form, cgpa: e.target.value })} />
            <Button sx={{ mt: 2 }} variant="contained" onClick={() => handleSave(form)}>
            Save
            </Button>
        </DialogContent>
        </Dialog>
    );
}